package com.three.gyangriha.service;

import com.three.gyangriha.model.dto.UserRegistrationDTO;
import com.three.gyangriha.model.dto.UserResponseDTO;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.model.entity.UserSubscription;
import com.three.gyangriha.repo.UserRepository;
import com.three.gyangriha.strategies.UserSearchStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Map<String, UserSearchStrategy> strategies;
    private final SubscriptionService subscriptionService;
    private final MailService mailService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, Map<String, UserSearchStrategy> strategyBeans, SubscriptionService subscriptionService, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.strategies = strategyBeans;
        this.subscriptionService = subscriptionService;
        this.mailService = mailService;
    }

    @Transactional
    public UserResponseDTO registerUser(UserRegistrationDTO userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        if (userRepository.findByMobileNo(userDto.getMobileNo()).isPresent()) {
            throw new IllegalArgumentException("Mobile number already exists.");
        }
        // Map DTO to Entity
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save user and return response DTO
        User savedUser = userRepository.save(user);
        UserSubscription userSubscribe = subscriptionService.subscribe(savedUser, userDto.getSubscriptionType(), userDto.getCustomPrice());
        return this.prepareMail(new UserResponseDTO(savedUser), userSubscribe);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<UserRegistrationDTO> searchUser(Map<String, String> queryParams) {
        // Build combined specification
        Specification<User> specification = queryParams.entrySet().stream()
                .map(entry -> {
                    UserSearchStrategy strategy = strategies.get(entry.getKey());
                    return (strategy != null) ? strategy.buildSpecification(entry.getValue()) : null;
                })
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()); // Always-true specification for empty params

        List<User> users = userRepository.findAll(specification);
        return users.stream()
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<User> findUserByIdProof(String idProofNo) {
        return userRepository.findByIdProofNo(idProofNo);
    }

    private UserRegistrationDTO convertToUserResponseDTO(User user) {
        return new UserRegistrationDTO(
                user.getId(),
                user.getName(),
                user.getAddress(),
                user.getMobileNo(),
                user.getEmail(),
                user.getPassword(),
                user.getIdProofNo(),
                user.getIdProofType()
        );
    }

    private UserResponseDTO prepareMail(UserResponseDTO userResponseDTO, UserSubscription userSubscribe) {
        // Send email notification
        mailService.sendRegistrationEmail(userResponseDTO.getEmail(), userResponseDTO.getName(),
                userSubscribe.getPlan().getPlanName(), userSubscribe.getStartDate().toString(),
                userSubscribe.getEndDate().toString(), userSubscribe.getPlan().getBasePrice().toString());
        return userResponseDTO;
    }
}