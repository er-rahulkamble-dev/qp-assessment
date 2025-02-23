package com.dev.grocery_service.mapper;

import com.dev.grocery_service.dto.GroceryItemDto;
import com.dev.grocery_service.entity.GroceryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class GroceryItemMapper {

    @Mapping(target = "groceryItemId", source = "groceryItemId")
    public abstract GroceryItem toGroceryItem(GroceryItemDto groceryItemDto);

    public abstract GroceryItemDto toGroceryItemDto(GroceryItem groceryItem);
}
