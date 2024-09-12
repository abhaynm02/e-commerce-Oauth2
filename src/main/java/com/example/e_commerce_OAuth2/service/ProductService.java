package com.example.e_commerce_OAuth2.service;

import com.example.e_commerce_OAuth2.dto.ProductDto;
import com.example.e_commerce_OAuth2.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ResponseEntity<HttpStatus>addProduct(ProductDto productDto, MultipartFile image);
    ResponseEntity<HttpStatus>editProduct(Long productId,ProductDto productDto,MultipartFile image);
    List<ProductResponseDto>findAllProduct();
    ProductResponseDto findByProductId(Long productId);
}
