package com.example.e_commerce_OAuth2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentResponseDto {
    String paymentId;
    String cardId;
    UUID orderId;
}
