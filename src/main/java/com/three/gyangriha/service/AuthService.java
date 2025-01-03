package com.three.gyangriha.service;

import com.three.gyangriha.model.dto.LoginRequestDTO;
import com.three.gyangriha.model.dto.UserRegistrationDTO;
import com.three.gyangriha.model.entity.User;
import com.three.gyangriha.repo.UserRepository;
import com.three.gyangriha.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User(dto);
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        userRepository.save(user);
    }

    public String login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!new BCryptPasswordEncoder().matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return jwtUtil.generateToken(user);
    }
}
