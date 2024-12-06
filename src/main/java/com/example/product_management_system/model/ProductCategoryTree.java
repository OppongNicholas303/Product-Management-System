package com.example.product_management_system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryTree {
    CategoryNode root;

    public void  insertCategory(String categoryName){
        root = insertCategoryRec(root, categoryName);
    }

    private CategoryNode insertCategoryRec(CategoryNode node, String categoryName){
        if(node == null){
            return new CategoryNode(categoryName);
        }

        int comparison = categoryName.compareTo(node.getCategoryName());
        if(comparison < 0){
            node.setLeft(insertCategoryRec(node.getLeft(), categoryName));
        }else {
            node.setRight(insertCategoryRec(node.getRight(), categoryName));
        }
        return node;
    }

    public List<Product> getProductInCategory(String categoryName){
        CategoryNode categoryNode = findCategory(root, categoryName);

        return categoryNode != null ? categoryNode.getProducts() : new ArrayList<>();
    }

    public void addProduct(Product product, String categoryName){
        CategoryNode categoryNode = findCategory(root, categoryName);
        if(categoryNode != null){
            categoryNode.getProducts().add(product);
        }
    }

    public List<Product> searchProductInCategory(String categoryName, String productName){
        CategoryNode categoryNode = findCategory(root, categoryName);
        if (categoryNode != null) {
            return categoryNode.getProducts()
                    .stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private CategoryNode findCategory(CategoryNode node, String categoryName){
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

}
