package com.dev.grocery_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "payment_tb")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus; // e.g., PENDING, COMPLETED, FAILED

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod; // e.g., CREDIT_CARD, PAYPAL, CASH_ON_DELIVERY

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    //OneToOne with Order (owning side)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order; // Each payment is associated with one order

    @Override
    public int hashCode() {
        return Objects.hash(paymentId); // Only use non-relationship fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId);
    }
}
