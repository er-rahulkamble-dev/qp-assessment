package com.dev.grocery_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private String paymentStatus;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private Double totalAmount;
    private OrderDto order;
}
