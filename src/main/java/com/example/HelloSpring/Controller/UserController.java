package com.example.HelloSpring.Controller;

import com.example.HelloSpring.Service.UserService;
import com.example.HelloSpring.dto.CreateUserDto;
import com.example.HelloSpring.dto.ListUserDto;
import com.example.HelloSpring.dto.LoginDto;
import com.example.HelloSpring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Thay đổi đường dẫn ở đây
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.ok("User created successfully");
    }

    // API đăng nhập
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = userService.loginUser(loginDto);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/getlist")
    public List<ListUserDto> getListUser() {
        return  userService.getListUser();
    }

    @DeleteMapping("/deleteEmail/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
         userService.deleteUserByEmail(email);
                return ResponseEntity.ok("xoá thành công Email:" + email );
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }


}
