package com.ink.shop.controller;

import com.ink.shop.model.User;
import com.ink.shop.service.UserService;
import com.ink.shop.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/me")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        Long userId = (long) request.getAttribute("id");
        Optional<User> userOpt = userService.getUserById(userId);
        return userOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @RequestBody User user) {
        Long userId = jwtUtil.extractUserId(String.valueOf(request));
        user.setId(userId);
        User updated = userService.updateUser(user);
        return ResponseEntity.ok(updated);
    }
}
