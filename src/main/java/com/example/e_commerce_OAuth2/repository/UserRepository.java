package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String username);
    boolean existsByEmail(String email);
}
