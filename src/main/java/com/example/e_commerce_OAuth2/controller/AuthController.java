package com.example.e_commerce_OAuth2.controller;

import com.example.e_commerce_OAuth2.dto.AuthResponse;
import com.example.e_commerce_OAuth2.dto.RegisterRequest;
import com.example.e_commerce_OAuth2.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final AuthenticationService authenticationService;
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody RegisterRequest request) {
        AuthResponse authResponse;
        // If the email exists, authenticate. If not, register the user.
        if (authenticationService.isEmailExist(request.getEmail())) {
            authResponse = authenticationService.authenticate(request);
        } else {
            authResponse = authenticationService.register(request);
        }
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

}
