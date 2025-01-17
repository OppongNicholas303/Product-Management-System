package com.example.product_management_system.dto;

import com.example.product_management_system.model.jpa.Product;

import java.util.List;

public record CategoryDTO(
        String categoryName,
        List<Product> products
) {
}
