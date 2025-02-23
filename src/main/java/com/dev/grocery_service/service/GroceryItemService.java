package com.dev.grocery_service.service;

import com.dev.grocery_service.dto.GroceryItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroceryItemService {

    List<GroceryItemDto> getAllGroceryItems();

    List<GroceryItemDto> addGroceryItems(List<GroceryItemDto> groceryItemList);

    String RemoveGroceries(List<GroceryItemDto> groceryItemDtoList);

    List<GroceryItemDto> updateGroceries(List<GroceryItemDto> groceryItemDtoList);

    List<GroceryItemDto> manageInventory(List<GroceryItemDto> groceryItemDtoList);
}
