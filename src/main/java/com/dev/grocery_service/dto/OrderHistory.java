package com.dev.grocery_service.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class OrderHistory {
    private BigInteger orderId;
    private List<OrderedItemDto> orderedItems = new ArrayList<>();
    private Double totalAmount;
    private String paymentStatus;
}
