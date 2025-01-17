package com.example.product_management_system.controller.jpa;

import com.example.product_management_system.dto.CategoryDTO;
import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.model.jpa.Product;
import com.example.product_management_system.service.jpa.CategoryService;
import com.example.product_management_system.service.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Profile("default")
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
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
    public ResponseEntity<List<Product>> searchProductInCategory(
          @RequestParam("category-name") String categoryName ,
          @RequestParam("product-name") String productName
    ){
        System.out.println(productName + " " + categoryName);
        List<Product> product = productService.searchProductInCategory(categoryName, productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{category-name}/products/{product-name}")
    public ResponseEntity<String> deleteProduct(
         @PathVariable("category-name") String categoryName,
         @PathVariable("product-name") String productName
    ){
       boolean deleted = productService.deleteProduct(productName, categoryName);
       return new ResponseEntity<>(deleted ? "Product deleted" : "Product not deleted", HttpStatus.OK);
    }

    @GetMapping("/products-by-category")
    public ResponseEntity<List<Product>>  getProductsByCategory(
            @RequestBody CategoryDTO categoryDTO
    ) {
        List<Product> products = productService.getProductsByCategory(categoryDTO.categoryName());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/find-by-page")
    public ResponseEntity<Page<Product>> findProductsByPage(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Page<Product> product = productService.getProductByPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/update-product/{category-name}/{product-name}/{id}")
    public void updateProduct(
            @RequestBody Product product,
            @PathVariable("id") String id,
            @PathVariable("category-name") String categoryName,
            @PathVariable("product-name") String productName
    ){
        productService.updateProduct(product, id, categoryName, productName);
    }

}
