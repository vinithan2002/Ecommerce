package com.ecommerce.online.Service.userAddress;


import com.ecommerce.online.dto.UserAddressDto;

import java.util.List;

public interface UserAddressService {
    List<UserAddressDto> getUserAddress(long userId);
    void addUserAddress(UserAddressDto userAddressDto);
    void updateUserAddress(UserAddressDto userAddressDto);
    void deleteUserAddress(Long id);
    void selectAddressAsDefault(Long id);
}
