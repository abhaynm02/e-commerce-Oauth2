package com.example.e_commerce_OAuth2.service.serviceImp;

import com.example.e_commerce_OAuth2.dto.OrderRequestDto;
import com.example.e_commerce_OAuth2.dto.OrderResponseDto;
import com.example.e_commerce_OAuth2.dto.PaymentRequestDto;
import com.example.e_commerce_OAuth2.dto.PaymentResponseDto;
import com.example.e_commerce_OAuth2.model.*;
import com.example.e_commerce_OAuth2.repository.OrderEntityRepository;
import com.example.e_commerce_OAuth2.repository.PaymentRepository;
import com.example.e_commerce_OAuth2.repository.ProductRepository;
import com.example.e_commerce_OAuth2.repository.UserRepository;
import com.example.e_commerce_OAuth2.service.OrderService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService {
    private final JwtService jwtService ;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final PaymentRepository paymentRepository;
    private final AttributeEncryptor attributeEncryptor;
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto requestDto, String token) {
        HashMap<Integer,Integer>orders=requestDto.getOrders();

        UserEntity user =findUserWhitJwt(token);
        OrderEntity order =new OrderEntity();
        order.setUser(user);
        List<OrderItem>orderItems=new ArrayList<>();
             orders.forEach((k,v)->{
                 OrderItem orderItem =new OrderItem();
                 Optional<Products> optionalProduct=productRepository.findById(Long.valueOf(k));
                 if (optionalProduct.isPresent()){
                     Products product=optionalProduct.get();
                     orderItem.setProducts(product);
                     orderItem.setQuantity(v);
                     orderItem.setTotalPrice(product.getPrice()*v);
                     orderItem.setProductsStatus(ProductStatus.PENDING);
                     orderItem.setOrders(order);
                     orderItems.add(orderItem);
                 }

             });
             order.setTotalAmount(totalAmount(orderItems));
             order.setOrderItems(orderItems);
             order.setOrderDate(LocalDateTime.now());
             order.setTotalItem(orderItems.size());
             OrderEntity pleaseOrder=orderEntityRepository.save(order);
             OrderResponseDto orderResponseDto=new OrderResponseDto();
             orderResponseDto.setOrderId(pleaseOrder.getId());
             orderResponseDto.setTotalAmount(pleaseOrder.getTotalAmount());
             orderResponseDto.setTotalItem(pleaseOrder.getTotalItem());



        return orderResponseDto;
    }

    @Override
    public PaymentResponseDto paymentRequest(PaymentRequestDto requestDto) {
        Optional<OrderEntity> optionalOrder=orderEntityRepository.findById(requestDto.getOrderId());
        if (optionalOrder.isPresent()){
            OrderEntity order=optionalOrder.get();
            Payment payment =new Payment();
            UUID uuid=UUID.randomUUID();
            String stringUUID=uuid.toString();
            payment.setPaymentId(stringUUID);
            payment.setOrder(order);
            payment.setCardNumber(attributeEncryptor.convertToDatabaseColumn(requestDto.getCardNumber()));
            payment.setDate(LocalDate.now());
            Payment result=  paymentRepository.save(payment);
            order.setPayment(payment);
            orderEntityRepository.save(order);
            PaymentResponseDto paymentResponseDto=new PaymentResponseDto();
            paymentResponseDto.setPaymentId(result.getPaymentId());
            paymentResponseDto.setOrderId(result.getOrder().getId());
            paymentResponseDto.setCardId(attributeEncryptor.convertToEntityAttribute(result.getCardNumber()));
            return paymentResponseDto;
        }
        return null;
    }

    private Float totalAmount(List<OrderItem>orderItems){
        return orderItems.stream().map(OrderItem::getTotalPrice).reduce(0.f,Float::sum);
    }
    private UserEntity findUserWhitJwt(String token){
        String userName=jwtService.extractUsername(token.substring(7));
        Optional<UserEntity> user=userRepository.findByEmail(userName);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new RuntimeException("user not found with this email");
        }

    }
}
