package com.ecommerce.online.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartDto {

    private Long cartId;
    private Long userId;
}