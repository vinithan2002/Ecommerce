package com.ecommerce.online.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@AllArgsConstructor
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long productId;
    private String productName;
    private String description;

    private BigDecimal price;
    private BigDecimal discountPrice;

    private String currency;
    private Integer quantityInStock;

    private Boolean isActive;
    private Long categoryId;
    private String brand;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDto() {

    }
}
