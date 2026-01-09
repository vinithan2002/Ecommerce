package com.ecommerce.online.Service.user;

import com.ecommerce.online.Entity.User;
import com.ecommerce.online.Repository.UserRepository;
import com.ecommerce.online.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<UserDto> getAllUsers()
    {
        List<User> user = userRepository.findAll();
//        List<UserDto> productDtoList = user.stream()
//                .map(users -> modelMapper.map(users, UserDto.class))
//                .toList();
        List<UserDto> productDtoList = user.stream()
                .map(users -> new UserDto(
                        users.getId(),
                        users.getName(),
                        users.getEmail(),
                        users.getPassword(),
                        users.getRole(),
                        users.getPhone(),
                        users.getIsActive(),
                        users.getCreatedAt(),
                        users.getUpdatedAt())).toList();
        return productDtoList;
    }


    public void createUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    public void updateUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        User user = userRepository.findById(id).get();
        user.setIsActive(false);
        userRepository.save(user);

    }

}
