package com.example.product_management_system.repository;

import com.example.product_management_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryName(String categoryName);
}
