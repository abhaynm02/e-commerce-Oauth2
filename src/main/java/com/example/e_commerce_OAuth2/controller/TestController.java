package com.example.e_commerce_OAuth2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("test work properly", HttpStatus.OK);
    }
}
