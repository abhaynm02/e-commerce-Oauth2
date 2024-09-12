package com.example.e_commerce_OAuth2.controller;

import com.example.e_commerce_OAuth2.dto.CategoryDto;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNameExistsException;
import com.example.e_commerce_OAuth2.exceptions.coustomexceptions.CategoryNotFoundException;
import com.example.e_commerce_OAuth2.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/AllCategory")
    public ResponseEntity<List<CategoryDto>>findAllCategory(@RequestHeader (name="Authorization") String token){
        System.out.println(token);
        return new ResponseEntity<>(categoryService.findAllCategory(),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus>addCategory(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/find/by/{categoryId}")
    public ResponseEntity<CategoryDto>findByCategoryId(@PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.findByCategoryId(categoryId),HttpStatus.OK);
    }
    @PostMapping("/edit/{categoryId}")
    public ResponseEntity<HttpStatus>editCategory(@PathVariable Long categoryId, @Valid @RequestBody  CategoryDto categoryDto) throws
            CategoryNameExistsException,
            CategoryNotFoundException {
        categoryService.editCategory(categoryId,categoryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
