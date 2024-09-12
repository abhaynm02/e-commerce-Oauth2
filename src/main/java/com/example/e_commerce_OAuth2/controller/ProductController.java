package com.example.e_commerce_OAuth2.controller;

import com.example.e_commerce_OAuth2.dto.ProductDto;
import com.example.e_commerce_OAuth2.dto.ProductResponseDto;
import com.example.e_commerce_OAuth2.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDto>>findAllProduct(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?>addProduct(@Valid @RequestPart("product")ProductDto request,
                                       @RequestPart("image") MultipartFile image){
        productService.addProduct(request,image);
        return new ResponseEntity<>("product added",HttpStatus.CREATED);
    }
    @GetMapping("/edit/{productId}")
    public ResponseEntity<?>editProduct(@PathVariable Long productId, @Valid @RequestPart("product")ProductDto productDto,@RequestPart("image")MultipartFile image){
        productService.editProduct(productId,productDto,image);
        return ResponseEntity.ok().build();
    }
}
