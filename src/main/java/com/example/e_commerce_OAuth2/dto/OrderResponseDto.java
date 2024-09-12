package com.example.e_commerce_OAuth2.dto;

import com.example.e_commerce_OAuth2.model.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto{
    UUID orderId;
    float totalAmount;
    int totalItem;


}
