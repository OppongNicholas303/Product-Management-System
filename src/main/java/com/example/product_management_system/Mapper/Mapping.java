package com.example.product_management_system.Mapper;

import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.model.jpa.Category;
import com.example.product_management_system.model.jpa.Product;
import com.example.product_management_system.model.mangodb.CategoryForMongodb;
import com.example.product_management_system.model.mangodb.ProductForMongodb;

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

    public ProductForMongodb toProductForMongo(ProductDTO productDTO) {
        ProductForMongodb product = new ProductForMongodb();
        product.setProductName(productDTO.productName());
        product.setProductDescription(productDTO.productDescription());
        product.setProductPrice(productDTO.productPrice());
        CategoryForMongodb category = new CategoryForMongodb();
        category.setCategoryIdString(String.valueOf(productDTO.category()));
        product.setCategory(category);
        return product;

    }
}
