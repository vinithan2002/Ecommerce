package com.ecommerce.online.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.results.graph.collection.internal.BagInitializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @Column(name = "total_amount", precision = 12 ,scale = 2)
    private BigDecimal totolAmount;

    @Column(name = "order_status", nullable = false)
    private String orderStatus = "SHIPPED";

    @Column(name = "payment_method")
    private String paymentMethod = "UPI";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
