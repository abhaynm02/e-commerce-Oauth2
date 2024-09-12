package com.example.e_commerce_OAuth2.controller;

import com.example.e_commerce_OAuth2.dto.ProductResponseDto;
import com.example.e_commerce_OAuth2.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/products")
    ResponseEntity<List<ProductResponseDto>>getAllProducts(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }


}
