package com.dev.grocery_service.service;

import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.dto.OrderDto;
import com.dev.grocery_service.dto.OrderHistory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderHistory placeAnOrder(String user, List<GroceryItemDto> items, String wayOfPayment);
}
