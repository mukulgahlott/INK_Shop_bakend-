package com.ink.shop.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_BASE64 = "cXdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NjQ=";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY_BASE64)); // 10 hours

    // Extract username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Long extractUserId(String token){
        return ((Integer) Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")).longValue();
    }


    public String generateToken(String username, long id , String role) {
        // Claims can be customized with more user data if needed
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("username", username);
        claims.put("id", id);

        // Generate and return the JWT token
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username));
    }


} 