package com.dev.grocery_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private BigInteger orderId;

    @Column(name="order_date")
    private LocalDateTime orderDate;

    @Column(name="order_status")
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk")
    @JsonBackReference
    private User user;

    // ManyToMany with GroceryItem, owning side with a join table
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "orders_grocery_items_tb",
            joinColumns = @JoinColumn(name = "order_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "grocery_item_id_fk")
    )
    private Set<GroceryItem> groceryItems = new HashSet<>(); // Initialize here;

    // OneToOne with Payment, mapped by order on Payment entity
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @Override
    public int hashCode() {
        return Objects.hash(orderId); // Only use non-relationship fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }
}
