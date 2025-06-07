package com.tattoo.inkshop.controller;

import com.tattoo.inkshop.dto.auth.AuthResponse;
import com.tattoo.inkshop.dto.auth.LoginRequest;
import com.tattoo.inkshop.dto.auth.RegisterRequest;
import com.tattoo.inkshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Login with email and password")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/google")
    @Operation(summary = "Login with Google OAuth2")
    public ResponseEntity<AuthResponse> loginWithGoogle(@RequestParam String token) {
        return ResponseEntity.ok(userService.loginWithGoogle(token));
    }
} 