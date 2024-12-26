package com.example.HelloSpring.Service;

import com.example.HelloSpring.Repository.ProductRepository;
import com.example.HelloSpring.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
       return productRepository.findAll();
    }

    public void CreateProduct(ProductEntity productEntity) {
        Optional<ProductEntity> nameProduct = productRepository.findByNameProduct(productEntity.getNameProduct());

      if (nameProduct.isPresent()) {
          throw new IllegalArgumentException("Product already exists");
      }
        ProductEntity product = new ProductEntity();
        product.setNameProduct(productEntity.getNameProduct());
        product.setPrice(productEntity.getPrice());

        ProductEntity savedProduct = productRepository.save(product);
    }
}
