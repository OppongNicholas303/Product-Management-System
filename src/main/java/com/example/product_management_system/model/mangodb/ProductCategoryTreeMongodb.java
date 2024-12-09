package com.example.product_management_system.model.mangodb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCategoryTreeMongodb {
    CategoryNodeMongodb root;

    public void  insertCategory(String categoryName){
        root = insertCategoryRec(root, categoryName);
    }

    private CategoryNodeMongodb insertCategoryRec(CategoryNodeMongodb node, String categoryName){
        if(node == null){
            return new CategoryNodeMongodb(categoryName);
        }

        int comparison = categoryName.compareTo(node.getCategoryName());
        if(comparison < 0){
            node.setLeft(insertCategoryRec(node.getLeft(), categoryName));
        }else {
            node.setRight(insertCategoryRec(node.getRight(), categoryName));
        }

        return node;
    }

    public List<ProductForMongodb> getProductInCategory(String categoryName){
        CategoryNodeMongodb categoryNode = findCategory(root, categoryName);

        return categoryNode != null ? categoryNode.getMongoProducts() : new ArrayList<>();
    }

    public void addProduct(ProductForMongodb product, String categoryName){
        CategoryNodeMongodb categoryNode = findCategory(root, categoryName);
        if(categoryNode != null){
            categoryNode.getMongoProducts().add(product);
        }
    }

    public List<ProductForMongodb> searchProductInCategory(String categoryName, String productName){
        CategoryNodeMongodb categoryNode = findCategory(root, categoryName);
        if (categoryNode != null) {
            return categoryNode.getMongoProducts()
                    .stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private CategoryNodeMongodb findCategory(CategoryNodeMongodb node, String categoryName){
        if(root == null || categoryName.equals(root.getCategoryName())){
            return node;
        }

        int comparison = categoryName.compareTo(node.getCategoryName());
        if(comparison < 0){
            return findCategory(node.getLeft(), categoryName);
        }else {
            return findCategory(node.getRight(), categoryName);
        }
    }

    public boolean deleteProduct(String productName, String categoryName) {
        System.out.println(root + " cliick");
        CategoryNodeMongodb categoryNode = findCategory(root, categoryName);
        if (categoryNode == null) {
            return false;
        }

        return categoryNode.getMongoProducts().removeIf(product -> product.getProductName().equals(productName));
    }


}
