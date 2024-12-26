package com.example.HelloSpring.Repository;

import com.example.HelloSpring.entity.UserEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(@Email(message = "Email phải đúng định dạng") String email);
    Optional<UserEntity> findById(String userId);

}
