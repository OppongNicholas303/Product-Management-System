package com.example.product_management_system.repository.jpa;

import com.example.product_management_system.model.jpa.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("default")
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);
    void deleteByProductName(String productName);
    Page<Product> findAllByProductName(String productName, Pageable pageable);
}
