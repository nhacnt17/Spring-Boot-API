package com.example.HelloSpring.Service;


import com.example.HelloSpring.Repository.CartItemRepository;
import com.example.HelloSpring.Repository.CartRepository;
import com.example.HelloSpring.Repository.ProductRepository;
import com.example.HelloSpring.Repository.UserRepository;
import com.example.HelloSpring.entity.CartEntity;
import com.example.HelloSpring.entity.CartItemEntity;
import com.example.HelloSpring.entity.ProductEntity;
import com.example.HelloSpring.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public String addProductToCart(String userId, String productId) {

        UserEntity user = userRepository.findById(userId).orElse(null);
        ProductEntity product = productRepository.findById(Long.valueOf(productId)).orElse(null);

        if (user == null || product == null) {
            return "User or Product not found!";
        }

        CartEntity cart = user.getCart();
        if (cart == null) {
            cart = new CartEntity();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);

        cart.getCartItems().add(cartItem);
        cart.setTotalPrice((int) (cart.getTotalPrice() + product.getPrice()));

        cartRepository.save(cart);
        return "Product added to cart!";
    }
}