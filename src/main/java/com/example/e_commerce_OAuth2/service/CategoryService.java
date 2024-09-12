package com.example.e_commerce_OAuth2.service;

import com.example.e_commerce_OAuth2.dto.CategoryDto;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNameExistsException;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    boolean isCategoryExists(String name);
    ResponseEntity<String> addCategory(CategoryDto categoryDto) ;
    void editCategory(Long id,CategoryDto categoryDto) throws CategoryNameExistsException, CategoryNotFoundException;
    List<CategoryDto>findAllCategory();

    CategoryDto findByCategoryId(Long categoryId);
}
