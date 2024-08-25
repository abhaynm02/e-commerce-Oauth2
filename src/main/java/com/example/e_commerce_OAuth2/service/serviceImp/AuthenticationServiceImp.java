package com.example.e_commerce_OAuth2.service.serviceImp;

import com.example.e_commerce_OAuth2.dto.AuthResponse;
import com.example.e_commerce_OAuth2.dto.RegisterRequest;
import com.example.e_commerce_OAuth2.model.Role;
import com.example.e_commerce_OAuth2.model.UserEntity;
import com.example.e_commerce_OAuth2.repository.UserRepository;
import com.example.e_commerce_OAuth2.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;

    public AuthenticationServiceImp(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    JwtService jwtService,
                                    AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
       UserEntity user =new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        //saving the user in database
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //creating jwt token
        user=userRepository.save(user);
        String token =jwtService.generateToken(user);
        return new AuthResponse(token,user.getRole(),user.getName(), user.getEmail());
    }

    @Override
    public AuthResponse authenticate(RegisterRequest request) {
        //verifying user given details using authenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //finding the user and creating JWT token for validation
        UserEntity user =userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return  new AuthResponse(token,user.getRole(),user.getName(),user.getEmail());
    }
}
