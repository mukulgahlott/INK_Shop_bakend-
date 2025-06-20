package com.ink.shop.controller;

import com.ink.shop.model.User;
import com.ink.shop.security.JwtUtil;
import com.ink.shop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = authService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        return authService.authenticateUser(email, password)
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole().name());
                    return ResponseEntity.ok(Map.of(
                            "token", token,
                            "user", user
                    ));
                })
                .orElse(ResponseEntity.ok(Map.of(
                        "message", "Invalid Credentials"
                )));
    }
} 