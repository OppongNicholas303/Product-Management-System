package com.example.product_management_system.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryNode {
    private String categoryName;
    private CategoryNode left;
    private CategoryNode right;
    private List<Product> products;

    public CategoryNode(
            String categoryName
    ){
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
        this.left = null;
        this.right = null;
    }


}
