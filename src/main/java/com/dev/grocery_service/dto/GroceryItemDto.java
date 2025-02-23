package com.dev.grocery_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@Data
public class GroceryItemDto {

    @NotNull(message = "grocery Item Id is required, please view the existing items and pickup from there")
    private BigInteger groceryItemId;
    private String name;
    private BigDecimal price;
    private String category;
    @Schema(description = "Please be careful while adding/updating Quantity here as a inventory level ")
    @NotNull(message = "please add quantity")
    private BigInteger inventoryLevel;
    private String description;
}
