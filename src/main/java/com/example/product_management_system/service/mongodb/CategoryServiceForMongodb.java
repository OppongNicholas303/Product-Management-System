package com.example.product_management_system.service.mongodb;

import com.example.product_management_system.exception.AlreadyExist;
import com.example.product_management_system.model.mangodb.CategoryForMongodb;
import com.example.product_management_system.model.mangodb.ProductCategoryTreeMongodb;
import com.example.product_management_system.model.mangodb.ProductForMongodb;
import com.example.product_management_system.repository.mongodb.CategoryRepositoryForMongodb;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class CategoryServiceForMongodb {
    private final CategoryRepositoryForMongodb categoryRepository;
    private ProductCategoryTreeMongodb productCategoryTree;


    public CategoryServiceForMongodb(
            CategoryRepositoryForMongodb categoryRepository,
            ProductCategoryTreeMongodb productCategoryTree
    ) {
        this.categoryRepository = categoryRepository;
        this.productCategoryTree = productCategoryTree;
    }

    @PostConstruct
    public void init() {
//        this.productCategoryTree = new ProductCategoryTree();
        List<CategoryForMongodb> categories = categoryRepository.findAll();
        for (CategoryForMongodb category : categories) {
            productCategoryTree.insertCategory(category.getCategoryName());
            for (ProductForMongodb product : category.getProductsForMongodb()) {
                productCategoryTree.addProduct(product, category.getCategoryName());
            }
        }

    }

    public void addCategory(String categoryName) {
        if (categoryRepository.findByCategoryName(categoryName).isEmpty()) {
            CategoryForMongodb category = new CategoryForMongodb();
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
            productCategoryTree.insertCategory(category.getCategoryName());
        }else {
            throw new AlreadyExist(categoryName + " already exist");
        }
    }

}
