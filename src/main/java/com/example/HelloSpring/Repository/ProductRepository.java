package com.example.HelloSpring.Repository;


import com.example.HelloSpring.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long > {
    Optional<ProductEntity> findByNameProduct(String name);
}
