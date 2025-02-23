package com.dev.grocery_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "grocery_item_tb")
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groc_itm_id")
    private BigInteger groceryItemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "inventory_level", nullable = false)
    private BigInteger inventoryLevel;

    @Column(name = "description")
    private String description;

    // ManyToMany - inverse side for the join
    @ManyToMany(mappedBy = "groceryItems", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Override
    public int hashCode() {
        return Objects.hash(groceryItemId); // Only use non-relationship fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryItem groceryItem = (GroceryItem) o;
        return Objects.equals(groceryItemId, groceryItem.groceryItemId);
    }

}
