package com.ink.shop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        // Simplified response as a Map
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 0);
        responseBody.put("message", "Access Denied: " + authException.getMessage());

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.print(mapper.writeValueAsString(responseBody));
        out.flush();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        // Simplified response as a Map
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 0);
        responseBody.put("message", "Access Denied: " + accessDeniedException.getMessage());

        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.print(mapper.writeValueAsString(responseBody));
        out.flush();
    }

}
