package com.example.e_commerce_OAuth2.repository;

import com.example.e_commerce_OAuth2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
}
