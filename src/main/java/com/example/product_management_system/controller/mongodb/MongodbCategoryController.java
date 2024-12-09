package com.example.product_management_system.controller.mongodb;

import com.example.product_management_system.dto.CategoryDTO;
import com.example.product_management_system.service.mongodb.CategoryServiceForMongodb;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("prod")
@RequestMapping("/api/v1")
public class MongodbCategoryController {
    private final CategoryServiceForMongodb categoryService;

    public MongodbCategoryController(CategoryServiceForMongodb categoryService) {
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
