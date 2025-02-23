package com.dev.grocery_service.repository;

import com.dev.grocery_service.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, BigInteger> {
}
