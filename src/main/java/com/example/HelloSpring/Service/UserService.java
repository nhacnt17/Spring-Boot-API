package com.example.HelloSpring.Service;


import com.example.HelloSpring.Repository.UserRepository;
import com.example.HelloSpring.dto.CreateUserDto;
import com.example.HelloSpring.dto.ListUserDto;
import com.example.HelloSpring.dto.LoginDto;
import com.example.HelloSpring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(CreateUserDto createUserDto) {
        Optional<UserEntity> userDto = userRepository.findByEmail(createUserDto.getEmail());
        if (userDto.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        } else {
            UserEntity user = new UserEntity();
            user.setName(createUserDto.getName());
            user.setPassword(createUserDto.getPassword());
            user.setEmail(createUserDto.getEmail());
            if (createUserDto.getRole() == "khongbiet") {
                user.setRole("ROLE_USER");
            } else {
                user.setRole("ROLE_ADMIN");
            }

            UserEntity savedUser = userRepository.save(user);
            System.out.println("User created successfully: " + savedUser.getName());
        }

    }

    public boolean loginUser(LoginDto loginDto) {
        Optional<UserEntity> user = userRepository.findByEmail(loginDto.getEmail());

        if (user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
            return true;
        }
        return false;
    }

    public List<ListUserDto> getListUser() {
        List<UserEntity> users = userRepository.findAll();

        if (!users.isEmpty()) {
            return users.stream()
                    .filter(user -> "ROLE_USER".equals(user.getRole())) // Lọc theo role
                    .map(user -> new ListUserDto(user.getName(), user.getEmail()))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleteUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
        throw new IllegalArgumentException("Emai not found");
    }

    // Phương thức lấy tất cả người dùng
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();  // Trả về tất cả bản ghi trong bảng userdemo
    }
}
