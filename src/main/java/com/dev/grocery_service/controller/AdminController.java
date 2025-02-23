package com.dev.grocery_service.controller;

import com.dev.grocery_service.config.CustomUserDetailsService;
import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.dto.OrderDto;
import com.dev.grocery_service.dto.UserDto;
import com.dev.grocery_service.service.GroceryItemService;
import com.dev.grocery_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "3. Hi Admins...!", description = "Perform all administrative tasks here...!") // Prefix with a number to control order
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GroceryItemService groceryItemService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //     Add new grocery items to the system
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/groceries")
    @Operation(summary = "2. Add new grocery items to the system", description = "add new grocery items") // Prefix with a number to control order
    public ResponseEntity<List<GroceryItemDto>> addGroceries(@RequestBody List<GroceryItemDto> groceryItemList) {
        List<GroceryItemDto> groceryItemDtoList = groceryItemService.addGroceryItems(groceryItemList);
        return new ResponseEntity<>(groceryItemDtoList, HttpStatus.OK);
    }

    //     View existing grocery items
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/groceries")
    @Operation(summary = "1. View existing grocery items", description = "copy the listed items to manage inventory or delete items") // Prefix with a number to control order
    public ResponseEntity<List<GroceryItemDto>> groceries() {
        List<GroceryItemDto> allGroceryItems = groceryItemService.getAllGroceryItems();
        return new ResponseEntity<>(allGroceryItems, HttpStatus.OK);
    }

    //     Remove grocery items from the system
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/groceries")
    @Operation(summary = "3. Remove grocery items from the system", description = "paste copied items and select to remove") // Prefix with a number to control order
    public ResponseEntity<String> removeGroceries(@RequestBody List<GroceryItemDto> groceryItemDtoList) {
        String deleteMsg = groceryItemService.RemoveGroceries(groceryItemDtoList);
        return ResponseEntity.ok(deleteMsg);
    }

    //     Update details (e.g., name, price) of existing grocery items
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/groceries")
    @Operation(summary = "4. Update details (e.g., name, price) of existing grocery items", description = "paste copied items and modify the fields you want to update") // Prefix with a number to control order
    public ResponseEntity<List<GroceryItemDto>> updateGroceries(@RequestBody List<GroceryItemDto> groceryItemDtoList) {
        List<GroceryItemDto> itemDtoList = groceryItemService.updateGroceries(groceryItemDtoList);
        return ResponseEntity.ok(itemDtoList);
    }

//     Manage inventory levels of grocery items
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/groceries-inventory")
    @Operation(summary = "5. Manage inventory of grocery items", description = "paste copied items in request body and modify the inventory level of it ") // Prefix with a number to control order
    public ResponseEntity<List<GroceryItemDto>> manageInventoryLevel(@RequestBody List<GroceryItemDto> groceryItemDtoList){
        List<GroceryItemDto> itemDtoList = groceryItemService.manageInventory(groceryItemDtoList);
        return ResponseEntity.ok(itemDtoList);
    }
}
