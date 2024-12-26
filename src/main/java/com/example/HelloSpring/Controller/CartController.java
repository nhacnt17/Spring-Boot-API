package com.example.HelloSpring.Controller;

import com.example.HelloSpring.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addProductToCart(@RequestParam String userId, @RequestParam String productId) {
        return cartService.addProductToCart(userId,productId);
    }
}
