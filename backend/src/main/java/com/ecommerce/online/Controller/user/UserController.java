package com.ecommerce.online.Controller.user;

import com.ecommerce.online.Entity.Role;
import com.ecommerce.online.Service.user.UserService;
import com.ecommerce.online.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

   @Autowired
   private UserService userService;

    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserByid(@PathVariable Long id)
    {
        return userService.getUserDetails(id);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserDto userDto)
    {
        userDto.setRole(Role.USER);
        userService.createUser(userDto);
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void createAdmin(@RequestBody UserDto userDto)
    {
        userDto.setRole(Role.ADMIN);
        userService.createUser(userDto);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody UserDto userDto)
    {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }

}
