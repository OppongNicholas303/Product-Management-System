package com.example.product_management_system.repository;

import com.example.product_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);
    void deleteByProductName(String productName);
}
