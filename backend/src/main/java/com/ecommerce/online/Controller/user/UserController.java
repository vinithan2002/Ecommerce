package com.ecommerce.online.Controller.user;

import com.ecommerce.online.Service.user.UserService;
import com.ecommerce.online.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto)
    {
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
