package com.example.product_management_system.Mapper;

import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.model.Category;
import com.example.product_management_system.model.Product;

public class Mapping {

    public Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.productName());
        product.setProductDescription(productDTO.productDescription());
        product.setProductPrice(productDTO.productPrice());
        Category category = new Category();
        category.setCategoryId(productDTO.category());
        product.setCategory(category);
        return product;

    }
}
