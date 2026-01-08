package com.example.Inxhinieri.Softi.auth.services;

import com.example.Inxhinieri.Softi.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final String secret = "mysecretkey123mysecretkey123mysecretkey123";
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    private final long expirationMs = 3600000; // 1 hour

    public String generate(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public Claims validate(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserId(String token) {
        return validate(token).getSubject();
    }

    public boolean isTokenValid(String token, User user) {
        final String userId = extractUserId(token);
        return (userId.equals(user.getName()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return validate(token).getExpiration().before(new Date());
    }
}
