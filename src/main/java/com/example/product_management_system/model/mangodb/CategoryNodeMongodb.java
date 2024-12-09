package com.example.product_management_system.model.mangodb;

import com.example.product_management_system.model.jpa.CategoryNode;
import com.example.product_management_system.model.jpa.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryNodeMongodb {
    private String categoryName;
    private CategoryNodeMongodb left;
    private CategoryNodeMongodb right;
    private List<ProductForMongodb> mongoProducts;

    public CategoryNodeMongodb(
            String categoryName
    ){
        this.categoryName = categoryName;
        this.left = null;
        this.right = null;
        mongoProducts = new ArrayList<>();
    }

}
