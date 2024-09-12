package com.example.e_commerce_OAuth2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private UUID id;
    private float totalAmount;
    private  int totalItem;
    @OneToOne
    private Payment payment;
    private LocalDateTime orderDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems=new ArrayList<>();
}
