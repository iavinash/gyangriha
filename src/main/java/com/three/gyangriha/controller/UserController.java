package com.three.gyangriha.controller;

import com.three.gyangriha.model.dto.UserRegistrationDTO;
import com.three.gyangriha.model.dto.UserResponseDTO;
import com.three.gyangriha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserRegistrationDTO>> searchUsers(@RequestParam Map<String, String> queryParams) {
        return ResponseEntity.ok(userService.searchUser(queryParams));
    }
}