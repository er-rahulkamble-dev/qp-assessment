package com.dev.grocery_service.service.Impl;

import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.entity.GroceryItem;
import com.dev.grocery_service.exception.DataNotFoundException;
import com.dev.grocery_service.mapper.GroceryItemMapper;
import com.dev.grocery_service.repository.GroceryItemRepository;
import com.dev.grocery_service.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroceryItemServiceImpl implements GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    private final GroceryItemMapper groceryItemMapper;

    @Override
    public List<GroceryItemDto> getAllGroceryItems() {
        return groceryItemRepository.findAll().stream().map(groceryItemMapper::toGroceryItemDto).toList();
    }

    @Override
    public List<GroceryItemDto> addGroceryItems(List<GroceryItemDto> groceryItemList) {
        List<GroceryItem> items = groceryItemList.stream().map(groceryItemMapper::toGroceryItem).toList();
        List<GroceryItem> groceryItems = groceryItemRepository.saveAll(items);
        return groceryItems.stream().map(groceryItemMapper::toGroceryItemDto).toList();
    }

    @Override
    public String RemoveGroceries(List<GroceryItemDto> groceryItemDtoList) {
        groceryItemRepository.deleteAll(groceryItemDtoList.stream().map(groceryItemMapper::toGroceryItem).toList());
        return groceryItemDtoList.size() + " Groceries deleted";
    }

    @Override
    public List<GroceryItemDto> updateGroceries(List<GroceryItemDto> groceryItemDtoList) {
        Set<BigInteger> groceryItemIds = groceryItemDtoList.stream().map(GroceryItemDto::getGroceryItemId)
                .collect(Collectors.toSet());
        List<GroceryItem> allById = groceryItemRepository.findAllById(groceryItemIds);
        Set<GroceryItem> items = groceryItemDtoList.stream().map(itemDto -> {
                    Optional<GroceryItem> item = allById.stream().filter(itm -> itm.getGroceryItemId().equals(itemDto.getGroceryItemId())).findFirst();
                    GroceryItem groceryItem = null;
                    if (item.isPresent()) {
                        groceryItem = item.get();
                        groceryItem.setName(itemDto.getName() != null ? itemDto.getName() : groceryItem.getName());
                        groceryItem.setCategory(itemDto.getCategory() != null ? itemDto.getCategory() : groceryItem.getCategory());
                        groceryItem.setPrice(itemDto.getPrice() != null ? itemDto.getPrice() : groceryItem.getPrice());
                        groceryItem.setInventoryLevel(itemDto.getInventoryLevel() != null ? itemDto.getInventoryLevel() : groceryItem.getInventoryLevel());
                        groceryItem.setDescription(itemDto.getDescription() != null ? itemDto.getDescription() : groceryItem.getDescription());
                    }
                    return groceryItem;
                }

        ).collect(Collectors.toSet());
        return groceryItemRepository.saveAll(items).stream().map(groceryItemMapper::toGroceryItemDto).toList();
    }

    @Override
    public List<GroceryItemDto> manageInventory(List<GroceryItemDto> groceryItemDtoList) {
        return groceryItemDtoList.stream()
                .map(groceryItemMapper::toGroceryItem)
                .map(gItem -> {
                    // Check if the grocery item exists using anyMatch for clarity
                    boolean exists = groceryItemRepository.findAll().stream()
                            .map(GroceryItem::getGroceryItemId)
                            .anyMatch(id -> id.equals(gItem.getGroceryItemId()));
                    if (!exists) {
                        throw new DataNotFoundException("Grocery item not found, " + gItem.getName());
                    }
                    groceryItemRepository.save(gItem);
                    return gItem;
                })
                .map(groceryItemMapper::toGroceryItemDto) // Convert back to DTO if needed
                .collect(Collectors.toList());
    }
}
