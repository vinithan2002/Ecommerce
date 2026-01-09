package com.ecommerce.online.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserAddressDto {
    private Long id;
    private Long userId;
    private String fullName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String addressType;
    private Boolean isDefault;
    private LocalDateTime createdAt;
}
