package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, UUID> {
}
