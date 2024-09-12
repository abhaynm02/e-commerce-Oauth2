package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
