package com.dev.grocery_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderedItemDto {
    String name;
    Integer qty;
    BigDecimal price;
    BigDecimal totalPrice;
}
