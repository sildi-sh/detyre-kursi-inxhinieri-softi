package com.example.Inxhinieri.Softi.auth.services;

import com.example.Inxhinieri.Softi.auth.dto.LoginRequest;
import com.example.Inxhinieri.Softi.auth.dto.LoginResponse;
import com.example.Inxhinieri.Softi.auth.dto.SignupRequest;
import com.example.Inxhinieri.Softi.user.model.User;
import com.example.Inxhinieri.Softi.user.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepo;
    // Keep BCryptPasswordEncoder for secure password hashing
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // JWT service is removed/commented out since it's not needed.

    // Update the constructor to remove JwtService dependency
    public AuthService(UserRepository repo) {
        this.userRepo = repo;
    }

    // --- SIGNUP Method (Unchanged) ---
    public User signup(SignupRequest req) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(req.email);
        user.setName(req.name);
        // ALWAYS hash the password, even without JWT!
        user.setPassword(encoder.encode(req.password));
        user.setRole(User.Role.valueOf(req.role));
        user.setCreatedAt(LocalDateTime.now());

        return userRepo.save(user);
    }

    // --- LOGIN Method (Modified to remove JWT generation) ---
    public LoginResponse login(LoginRequest req) {
        var user = userRepo.findByEmail(req.email);

        // Check if user exists
        if (user.isEmpty()) {
            return new LoginResponse(false, null, "Invalid credentials");
        }

        User u = user.get();

        // Check if password matches
        if (!encoder.matches(req.password, u.getPassword())) {
            return new LoginResponse(false, null, "Invalid credentials");
        }

        // On successful login, return the User ID in the token field
        // to pass data back to the client (this replaces the token).
        String identifier = u.getId();

        return new LoginResponse(true, identifier, "Login successful");
    }
}