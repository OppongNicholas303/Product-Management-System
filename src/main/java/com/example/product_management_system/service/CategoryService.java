package com.example.product_management_system.service;

import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.exception.AlreadyExist;
import com.example.product_management_system.model.Category;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.model.ProductCategoryTree;
import com.example.product_management_system.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private ProductCategoryTree productCategoryTree;


    public CategoryService(
            CategoryRepository categoryRepository,
            ProductCategoryTree productCategoryTree
    ) {
        this.categoryRepository = categoryRepository;
        this.productCategoryTree = productCategoryTree;
    }

    @PostConstruct
    public void init() {
//        this.productCategoryTree = new ProductCategoryTree();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            productCategoryTree.insertCategory(category.getCategoryName());
            for (Product product : category.getProducts()) {
                productCategoryTree.addProduct(product, category.getCategoryName());
            }
        }

    }

    public void addCategory(String categoryName) {
        if (categoryRepository.findByCategoryName(categoryName).isEmpty()) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
            productCategoryTree.insertCategory(category.getCategoryName());
        }else {
            throw new AlreadyExist(categoryName + " already exist");
        }
    }

}
