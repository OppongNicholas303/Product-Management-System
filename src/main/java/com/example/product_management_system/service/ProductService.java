package com.example.product_management_system.service;

import com.example.product_management_system.Mapper.Mapping;
import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.exception.AlreadyExist;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.model.ProductCategoryTree;
import com.example.product_management_system.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
private ProductRepository productRepository;
private ProductCategoryTree productCategoryTree;


public ProductService(
        ProductRepository productRepository
        , ProductCategoryTree productCategoryTree)
{
    this.productRepository = productRepository;
    this.productCategoryTree = productCategoryTree;
}


public void addProduct(ProductDTO productDTO) {
   try {
       if(productRepository.findByProductName(productDTO.productName()).isPresent()){
           throw new AlreadyExist("Product already exist");
       }
       Mapping productMapping = new Mapping();
       Product product = productMapping.toProduct(productDTO);
       productRepository.save(product);
   }catch (Exception e){
       e.printStackTrace();
   }
}

public List<Product> searchProductInCategory(String categoryName, String productName) {
    return productCategoryTree.searchProductInCategory(categoryName, productName);
}

@Transactional
public boolean deleteProduct(String productName, String categoryName) {
  boolean deleted = productCategoryTree.deleteProduct(productName, categoryName);
  if(deleted){
      productRepository.deleteByProductName(productName);
  }
    System.out.println(deleted);
  return deleted;
}

    public  List<Product> getProductsByCategory(String categoryName) {
        return productCategoryTree.getProductInCategory(categoryName);
    }


}
