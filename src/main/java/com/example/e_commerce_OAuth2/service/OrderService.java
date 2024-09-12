package com.example.e_commerce_OAuth2.service;

import com.example.e_commerce_OAuth2.dto.OrderRequestDto;
import com.example.e_commerce_OAuth2.dto.OrderResponseDto;
import com.example.e_commerce_OAuth2.dto.PaymentRequestDto;
import com.example.e_commerce_OAuth2.dto.PaymentResponseDto;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto requestDto,String token);
    PaymentResponseDto paymentRequest(PaymentRequestDto requestDto);
}
