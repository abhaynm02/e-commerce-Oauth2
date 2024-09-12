package com.example.e_commerce_OAuth2.controller;

import com.example.e_commerce_OAuth2.dto.OrderRequestDto;
import com.example.e_commerce_OAuth2.dto.OrderResponseDto;
import com.example.e_commerce_OAuth2.dto.PaymentRequestDto;
import com.example.e_commerce_OAuth2.dto.PaymentResponseDto;
import com.example.e_commerce_OAuth2.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/place")
    ResponseEntity<OrderResponseDto>placeOrder(@RequestBody OrderRequestDto requestDto, @RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(orderService.placeOrder(requestDto,token), HttpStatus.CREATED);
    }
    @PostMapping("/payment")
    ResponseEntity<PaymentResponseDto>payment(@RequestBody PaymentRequestDto requestDto){
        return new ResponseEntity<>(orderService.paymentRequest(requestDto),HttpStatus.OK);
    }
}
