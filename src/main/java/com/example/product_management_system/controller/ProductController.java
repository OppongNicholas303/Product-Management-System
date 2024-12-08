package com.example.product_management_system.controller;

import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(
           @RequestBody ProductDTO productDTO
    ) {
        productService.addProduct(productDTO);
        return new ResponseEntity<>("Product added", HttpStatus.OK);
    }

    @GetMapping("/search-product-in-category")
    public ResponseEntity<List<Product>> searchProductInCategory(
          @RequestParam("category-name") String categoryName ,
          @RequestParam("product-name") String productName
    ){
        System.out.println(productName + " " + categoryName);
        List<Product> product = productService.searchProductInCategory(categoryName, productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
