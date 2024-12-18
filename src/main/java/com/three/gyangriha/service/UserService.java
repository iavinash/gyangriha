package com.three.gyangriha.service;

import com.three.gyangriha.model.dto.UserRegistrationDto;
import com.three.gyangriha.model.dto.UserResponseDto;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(UserRegistrationDto userDto) {
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
        return new UserResponseDto(savedUser);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContaining(query);
    }

    public Optional<User> findUserByIdProof(String idProofNo) {
        return userRepository.findByIdProofNo(idProofNo);
    }
}