package com.example.e_commerce_OAuth2.dto;

import com.example.e_commerce_OAuth2.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
