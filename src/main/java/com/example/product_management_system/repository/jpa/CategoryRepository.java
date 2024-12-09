package com.example.product_management_system.repository.jpa;

import com.example.product_management_system.model.jpa.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Profile("default")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryName(String categoryName);
}
