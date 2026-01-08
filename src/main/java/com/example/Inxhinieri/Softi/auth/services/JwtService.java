//package com.example.Inxhinieri.Softi.auth.services;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//
//@Service
//public class JwtService {
//
//    // Make sure the secret is long enough (32+ bytes for HS256)
//    private final String secret = "mysecretkey123mysecretkey123mysecretkey123";
//    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//    private final long expirationMs = 3600000; // 1 hour
//
//    public String generate(String userId, String email, String role) {
//        return Jwts.builder()
//                .setSubject(userId)
//                .claim("email", email)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                .signWith(key) // this works with SecretKey
//                .compact();
//    }
//
//    public Claims validate(String token) {
//        return Jwts.parser()
//                .setSigningKey(key) // parserBuilder() exists in 0.12.7 with jjwt-impl
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
