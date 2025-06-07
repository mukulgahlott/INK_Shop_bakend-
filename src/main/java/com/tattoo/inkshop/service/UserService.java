package com.tattoo.inkshop.service;

import com.tattoo.inkshop.dto.auth.AuthResponse;
import com.tattoo.inkshop.dto.auth.LoginRequest;
import com.tattoo.inkshop.dto.auth.RegisterRequest;
import com.tattoo.inkshop.model.User;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse loginWithGoogle(String googleToken);
    User getCurrentUser();
    User updateUser(User user);
    void deleteUser(Long id);
} 