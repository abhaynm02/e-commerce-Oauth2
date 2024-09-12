package com.example.e_commerce_OAuth2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDto {
    @NotNull(message = "orderId is required")
    private UUID orderId;

    @NotNull(message = "CardNumber is required")
    @Size(min = 13, max = 19, message = "CardNumber must be between 13 and 19 digits")
    @Pattern(regexp = "\\d+", message = "CardNumber must contain only digits")
    private String cardNumber;
}
