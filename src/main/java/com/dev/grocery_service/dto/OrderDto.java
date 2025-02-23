package com.dev.grocery_service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {

    private Set<GroceryItemDto> groceryItemList;
    private UserDto userDto;
    private PaymentDto paymentDto;
}
