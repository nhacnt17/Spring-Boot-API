package com.example.HelloSpring.Controller;


import com.example.HelloSpring.Service.ProductService;
import com.example.HelloSpring.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getlist")
    public List<ProductEntity> getProduct() {
        return productService.getAllProducts();
    }

   @PostMapping("/CreateProduct")
    public ProductEntity CreateProduct(@RequestBody ProductEntity product) {
        productService.CreateProduct(product);
        return product;
   }
}
