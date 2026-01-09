package com.ecommerce.online.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private Long userId;
    private Integer cartId;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String paymentMethod;
    private LocalDateTime createdAt;

    public OrderDto() {

    }
}
