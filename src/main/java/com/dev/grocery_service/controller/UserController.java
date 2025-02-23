package com.dev.grocery_service.controller;

import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.dto.OrderDto;
import com.dev.grocery_service.dto.OrderHistory;
import com.dev.grocery_service.service.GroceryItemService;
import com.dev.grocery_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "2. Hi Users...!", description = "Here you can perform all user related tasks") // Prefix with a number to control order
public class UserController {

    private final GroceryItemService groceryItemService;

    private final OrderService orderService;

    @GetMapping("/grocery-items")
    @Operation(summary = "1. view the available grocery items", description = "check available grocery items here, copy the require items from here and use those to place order") // Prefix with a number to control order
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItems() {
        return ResponseEntity.ok(groceryItemService.getAllGroceryItems());
    }

//    Ability to book multiple grocery items in a single order
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/place-order")
    @Operation(summary = "2. please mention the quantity require in the field of inventorylevel")
    public ResponseEntity<OrderHistory> placeOrderAndCheckHistory(@RequestBody List<GroceryItemDto> groceryItemDtoList, @RequestParam("paymentMethod") String wayOfPayment){
        // Extract the username from the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the username of the authenticated use
        OrderHistory orderHistory = orderService.placeAnOrder(username, groceryItemDtoList,  wayOfPayment);
        return ResponseEntity.ok(orderHistory);
    }
}
