package com.example.e_commerce_OAuth2.model;

import com.example.e_commerce_OAuth2.service.serviceImp.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
     private OrderEntity order;
    private String paymentId;
    @Convert(converter = AttributeEncryptor.class)
    private String cardNumber;
    private LocalDate date;
}
