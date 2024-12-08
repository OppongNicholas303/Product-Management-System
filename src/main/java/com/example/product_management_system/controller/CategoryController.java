package com.example.product_management_system.controller;

import com.example.product_management_system.dto.CategoryDTO;
import com.example.product_management_system.model.Category;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory(
            @RequestBody CategoryDTO categoryDTO
    ) {

        categoryService.addCategory(categoryDTO.categoryName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created");
    }

}
