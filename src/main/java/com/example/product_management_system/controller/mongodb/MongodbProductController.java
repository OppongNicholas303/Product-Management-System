package com.example.product_management_system.controller.mongodb;

import com.example.product_management_system.dto.CategoryDTO;
import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.model.mangodb.ProductForMongodb;
import com.example.product_management_system.service.mongodb.CategoryServiceForMongodb;
import com.example.product_management_system.service.mongodb.ProductServiceForMongodb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Profile("prod")
@RequestMapping("/api/v1/product")
public class MongodbProductController {
    private ProductServiceForMongodb productService;
    private CategoryServiceForMongodb categoryService;

    @Autowired
    public MongodbProductController(ProductServiceForMongodb productService, CategoryServiceForMongodb categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(
            @RequestBody ProductDTO productDTO
    ) {
        productService.addProduct(productDTO);
        return new ResponseEntity<>("Product added", HttpStatus.OK);
    }

    @GetMapping("/search-product-in-category")
    public ResponseEntity<List<ProductForMongodb>> searchProductInCategory(
            @RequestParam("category-name") String categoryName ,
            @RequestParam("product-name") String productName
    ){
        System.out.println(productName + " " + categoryName);
        List<ProductForMongodb> product = productService.searchProductInCategory(categoryName, productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{category-name}/products/{product-name}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("category-name") String categoryName,
            @PathVariable("product-name") String productName
    ){
        System.out.println(productName + " " + categoryName);
        boolean deleted = productService.deleteProduct(productName, categoryName);
        return new ResponseEntity<>(deleted ? "Product deleted" : "Product not deleted", HttpStatus.OK);
    }

    @GetMapping("/products-by-category")
    public ResponseEntity<List<ProductForMongodb>>  getProductsByCategory(
            @RequestBody CategoryDTO categoryDTO
    ) {
        List<ProductForMongodb> products = productService.getProductsByCategory(categoryDTO.categoryName());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
