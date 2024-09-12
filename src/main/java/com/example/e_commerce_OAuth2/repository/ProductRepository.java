package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
}
