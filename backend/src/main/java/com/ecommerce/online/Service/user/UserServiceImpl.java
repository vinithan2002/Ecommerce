package com.ecommerce.online.Service.user;

import com.ecommerce.online.Entity.User;
import com.ecommerce.online.Repository.UserRepository;
import com.ecommerce.online.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static com.ecommerce.online.Entity.Role.USER;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  PasswordEncoder passwordEncoder;


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

    public UserDto getUserDetails(Long id)
    {
        Optional<User> user = userRepository.findById(id);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }


    public void createUser(UserDto userDto)
    {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent())
        {
            throw new RuntimeException("User Already Exist");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
