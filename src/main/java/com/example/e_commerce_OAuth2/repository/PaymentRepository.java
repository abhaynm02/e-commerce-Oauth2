package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
