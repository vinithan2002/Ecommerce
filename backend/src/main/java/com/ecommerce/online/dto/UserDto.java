package com.ecommerce.online.dto;

import com.ecommerce.online.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String phone;
    private Boolean is_active;
    private LocalDateTime  created_at;
    private LocalDateTime updated_at;
}
