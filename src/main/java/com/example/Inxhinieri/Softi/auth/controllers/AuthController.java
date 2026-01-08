package com.example.Inxhinieri.Softi.auth.controllers;

import com.example.Inxhinieri.Softi.auth.dto.LoginRequest;
import com.example.Inxhinieri.Softi.auth.dto.LoginResponse;
import com.example.Inxhinieri.Softi.auth.dto.SignupRequest;
import com.example.Inxhinieri.Softi.auth.services.AuthService;

import org.springframework.http.HttpStatus; // Import HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // --- SIGNUP Endpoint: Should return 201 Created ---
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        // Assuming service.signup() returns the created User object on success
        var user = service.signup(req);

        // Use ResponseEntity.status(HttpStatus.CREATED) for resource creation
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // --- LOGIN Endpoint: Should return 200 OK or 401 Unauthorized ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        LoginResponse response = service.login(req);

        if (response.success) {
            // Return 200 OK on successful login (with token in body)
            return ResponseEntity.ok(response);
        } else {
            // Return 401 Unauthorized for invalid credentials
            // and include the error message in the body
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.message);
        }
    }

    // --- LOGOUT Endpoint: Remains 200 OK ---
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // JWT logout is purely client-side by deleting the token.
        // The server confirms the request was processed successfully.
        return ResponseEntity.ok("Logged out successfully.");
    }
}