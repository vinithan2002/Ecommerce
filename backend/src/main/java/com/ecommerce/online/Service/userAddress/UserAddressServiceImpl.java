package com.ecommerce.online.Service.userAddress;


import com.ecommerce.online.Entity.User;
import com.ecommerce.online.Entity.UserAddress;
import com.ecommerce.online.Repository.UserAddressRepository;
import com.ecommerce.online.dto.UserAddressDto;
import com.ecommerce.online.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService{

    private final UserAddressRepository userAddressRepository;
    private final ModelMapper modelMapper;

    public List<UserAddressDto> getUserAddress(long userId)
    {
        List<UserAddress> address = userAddressRepository.findAllByUserId(userId);
        List<UserAddressDto> userAddressDtos = address.stream()
                .map(user_addresses -> new UserAddressDto(
                        user_addresses.getId(),
                        user_addresses.getUserId(),
                        user_addresses.getFullName(),
                        user_addresses.getPhone(),
                        user_addresses.getAddressLine1(),
                        user_addresses.getAddressLine2(),
                        user_addresses.getCity(),
                        user_addresses.getState(),
                        user_addresses.getCountry(),
                        user_addresses.getPincode(),
                        user_addresses.getAddressType(),
                        user_addresses.getIsDefault(),
                        user_addresses.getCreatedAt())).toList();

        return userAddressDtos;

    }

    public void addUserAddress(UserAddressDto userAddressDto)
    {
        UserAddress address = modelMapper.map(userAddressDto, UserAddress.class);
        Long tempId = address.getUserId();
        List<UserAddress> tempList = userAddressRepository.findAllByUserId(tempId);
        if(tempList.size() == 0)
        {
            address.setIsDefault(true);
            userAddressRepository.save(address);
        }
        else if (address.getIsDefault() == null || address.getIsDefault() == false)
        {
            address.setIsDefault(false);
            userAddressRepository.save(address);
        }
        else
        {
            userAddressRepository.setFalseToDefault(tempId);
            userAddressRepository.save(address);
        }

    }

    public void selectAddressAsDefault(Long id)
    {

        UserAddress userAddress = userAddressRepository.findById(id).get();
        Long userId = userAddress.getUserId();
        userAddressRepository.setFalseToDefault(userId);
        userAddress.setIsDefault(true);
        userAddressRepository.save(userAddress);
    }

    public void updateUserAddress(UserAddressDto userAddressDto)
    {
        UserAddress address = modelMapper.map(userAddressDto, UserAddress.class);
        userAddressRepository.save(address);
    }

    public void deleteUserAddress(Long id)
    {
        UserAddress address = userAddressRepository.findById(id).get();
        address.setIsDefault(false);
        userAddressRepository.save(address);
    }



}
