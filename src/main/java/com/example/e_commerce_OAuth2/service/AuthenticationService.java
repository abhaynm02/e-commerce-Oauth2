package com.example.e_commerce_OAuth2.service;

import com.example.e_commerce_OAuth2.dto.AuthResponse;
import com.example.e_commerce_OAuth2.dto.RegisterRequest;

public interface AuthenticationService {
    boolean isEmailExist(String email);
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(RegisterRequest request);

}
