package com.ecommerce.online.Controller.userAddress;

import com.ecommerce.online.Service.userAddress.UserAddressService;
import com.ecommerce.online.dto.UserAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("/userAddress/{userId}")
    public List<UserAddressDto> getUserAddress(@PathVariable Long userId)
    {
        return userAddressService.getUserAddress(userId);
    }

    @PostMapping("/userAddress")
    public void addUserAddress(@RequestBody UserAddressDto userAddressDto)
    {
         userAddressService.addUserAddress(userAddressDto);
    }

    @PutMapping("/userAddress")
    public void updateUserAddress(@RequestBody UserAddressDto userAddressDto)
    {
        userAddressService.updateUserAddress(userAddressDto);
    }

    @DeleteMapping("/userAddress/{id}")
    public void deleteUserAddress(@PathVariable Long id)
    {
        userAddressService.deleteUserAddress(id);
    }

    @PatchMapping("/selectAddress/{id}")
    public void selectAddressAsDefault(@PathVariable Long id)
    {
        userAddressService.selectAddressAsDefault(id);
    }


}
