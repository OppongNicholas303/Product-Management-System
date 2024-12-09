package com.example.product_management_system.repository.jpa;

import com.example.product_management_system.model.jpa.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("default")
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);
    void deleteByProductName(String productName);
}
