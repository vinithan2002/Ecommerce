package com.ecommerce.online.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemsDto {
    private Integer id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
