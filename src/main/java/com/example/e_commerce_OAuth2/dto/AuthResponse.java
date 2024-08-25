package com.example.e_commerce_OAuth2.dto;

import com.example.e_commerce_OAuth2.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private  Role role;
    private String name;
    private String email;
}
