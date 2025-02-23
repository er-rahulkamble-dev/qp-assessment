package com.dev.grocery_service.service.Impl;

import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.dto.OrderDto;
import com.dev.grocery_service.dto.OrderHistory;
import com.dev.grocery_service.dto.OrderedItemDto;
import com.dev.grocery_service.entity.GroceryItem;
import com.dev.grocery_service.entity.Order;
import com.dev.grocery_service.entity.Payment;
import com.dev.grocery_service.entity.User;
import com.dev.grocery_service.exception.DataNotFoundException;
import com.dev.grocery_service.exception.LowInventoryException;
import com.dev.grocery_service.mapper.GroceryItemMapper;
import com.dev.grocery_service.repository.GroceryItemRepository;
import com.dev.grocery_service.repository.OrderRepository;
import com.dev.grocery_service.repository.UserRepository;
import com.dev.grocery_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final GroceryItemRepository groceryItemRepository;

    private final GroceryItemMapper groceryItemMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return List.of();
    }

    @Transactional
    @Override
    public OrderHistory placeAnOrder(String userName, List<GroceryItemDto> itemDtoList, String wayOfPayment) {

        OrderHistory orderHistory = new OrderHistory();

        // 1. Retrieve the user by userName
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // 2. Create a new Order and set the User
        Order order = new Order();
        order.setUser(user);

        // Initialize totalAmount to accumulate order total
        BigDecimal totalAmount = BigDecimal.ZERO;

        Set<GroceryItem> items = itemDtoList.stream().map(groceryItemMapper::toGroceryItem).collect(Collectors.toSet());

        // 3. Process each grocery item provided in the order
        for (GroceryItem orderItem : items) {
            // Fetch the managed GroceryItem from the repository to ensure it is current and managed.
            GroceryItem managedItem = groceryItemRepository.findById(orderItem.getGroceryItemId())
                    .orElseThrow(() -> new DataNotFoundException("Grocery item not found"));

            // Retrieve the quantity requested by the user.
            // Here we assume orderItem.getRequestedQuantity() returns the quantity the user wants to purchase.
            BigInteger requestedQuantity = orderItem.getInventoryLevel();

            // Validate if there is in sufficient inventory.
            if (managedItem.getInventoryLevel().intValue() < requestedQuantity.intValue()) {
                throw new LowInventoryException("Insufficient inventory for selected item: " + managedItem.getName());
            }

            // 3a. Update inventory level by subtracting the requested quantity.
            managedItem.setInventoryLevel(managedItem.getInventoryLevel().subtract(requestedQuantity));

            // 3b. Associate the grocery item with the Order.
            // In a bidirectional ManyToMany association, update both sides.
            order.getGroceryItems().add(managedItem);
            managedItem.getOrders().add(order);

            // 3c. Calculate the total price for this item (price * requestedQuantity)
            BigDecimal itemTotal = managedItem.getPrice().multiply(BigDecimal.valueOf(requestedQuantity.intValue()));
            totalAmount = totalAmount.add(itemTotal);

            //maintain OrderHistory for ordered items
            orderHistory.getOrderedItems().add(OrderedItemDto.builder().name(managedItem.getName())
                    .qty(orderItem.getInventoryLevel().intValue())
                            .price(managedItem.getPrice())
                            .totalPrice(itemTotal)
                    .build());
        }

        // 4. Persist the Order; cascading will manage associations to GroceryItems.
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("COMPLETED");
        Order savedOrder = orderRepository.save(order);

        //associate orderId with OrderHistory
        orderHistory.setOrderId(savedOrder.getOrderId());

        // 5. Create and associate Payment with the Order.
        Payment payment = new Payment();
        payment.setPaymentMethod(wayOfPayment);
        payment.setTotalAmount(totalAmount);
        payment.setPaymentStatus("COMPLETED");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setOrder(savedOrder);


        // Set Payment in Order. With CascadeType.ALL, saving order will persist Payment.
        savedOrder.setPayment(payment);

        // Order history to say payment status
        orderHistory.setTotalAmount(totalAmount.doubleValue());
        orderHistory.setPaymentStatus(payment.getPaymentStatus());

        // 6. Optionally, re-save the Order if necessary (to persist Payment changes).
//        orderRepository.save(savedOrder);
        return orderHistory;
    }

}
