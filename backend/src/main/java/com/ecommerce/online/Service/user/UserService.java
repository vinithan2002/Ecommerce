package com.ecommerce.online.Service.user;

import com.ecommerce.online.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface UserService {
     List<UserDto> getAllUsers();
     public void createUser(UserDto userDto);
     public void updateUser(UserDto userDto);
     public void deleteUser(Long id);
}
