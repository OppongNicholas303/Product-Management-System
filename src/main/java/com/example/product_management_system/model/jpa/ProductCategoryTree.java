package com.example.product_management_system.model.jpa;

import com.example.product_management_system.exception.NotFound;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCategoryTree {
    CategoryNode root;

    //Insert Category as node
    public void  insertCategory(String categoryName){
        root = insertCategoryRec(root, categoryName);
    }

//    Recursive function to set the node
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

    public List<Product> getProductsInCategory(String categoryName)throws NotFound {

        CategoryNode categoryNode = findCategory(root, categoryName);

        if(categoryNode == null){
            throw new NotFound("Category '" + categoryName + "' not found.");
        }

        return categoryNode.getProducts();
    }

    public void addProduct(Product product, String categoryName)throws NotFound {
        CategoryNode categoryNode = findCategory(root, categoryName);
        if(categoryNode == null){
            throw new NotFound("Category '" + categoryName + "' not found to add product.");
        }
        categoryNode.getProducts().add(product);
    }

    public List<Product> searchProductsInCategory(String categoryName, String productName) throws NotFound {
        CategoryNode categoryNode = findCategory(root, categoryName);

        if(categoryNode == null){
            throw new NotFound("Category '" + categoryName + "' not found.");
        }

        List<Product> searchProducts = categoryNode.getProducts()
                    .stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .collect(Collectors.toList());

        if (searchProducts.isEmpty()) {
            throw new NotFound("No product named '" + productName + "' found in category '" + categoryName + "'.");
        }

        return searchProducts;
    }



    public boolean deleteProduct(String productName, String categoryName) {

        CategoryNode categoryNode = findCategory(root, categoryName);

        if (categoryNode == null) {
            throw new NotFound("Category '" + categoryName + "' not found to delete product '" + productName + "'.");
        }

        return categoryNode.getProducts().removeIf(product -> product.getProductName().equals(productName));
    }

    public void updateProduct(String categoryName, String productName, Product updatedProduct) throws NotFound {

        CategoryNode categoryNode = findCategory(root, categoryName);

        if (categoryNode == null) {
            throw new NotFound("Category '" + categoryName + "' not found to update product.");
        }

        List<Product> products = categoryNode.getProducts();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductName().equalsIgnoreCase(productName)) {
                products.set(i, updatedProduct);
                return;
            }
        }
        throw new NotFound("Product '" + productName + "' not found in category '" + categoryName + "'.");
    }


    private CategoryNode findCategory(CategoryNode node, String categoryName){
        if (node == null) {
            return null;
        }

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
