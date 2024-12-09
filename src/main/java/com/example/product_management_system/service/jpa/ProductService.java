package com.example.product_management_system.service.jpa;

import com.example.product_management_system.Mapper.Mapping;
import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.exception.AlreadyExist;
import com.example.product_management_system.model.jpa.Product;
import com.example.product_management_system.model.jpa.ProductCategoryTree;
import com.example.product_management_system.repository.jpa.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@Profile("default")
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
       if(productRepository.findByProductName(productDTO.productName()).isPresent()){
           throw new AlreadyExist("Product already exist");
        }
       Mapping productMapping = new Mapping();
       Product product = productMapping.toProduct(productDTO);
       productRepository.save(product);
}


public List<Product> searchProductInCategory(String categoryName, String productName) {
    return productCategoryTree.searchProductsInCategory(categoryName, productName);
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
        return productCategoryTree.getProductsInCategory(categoryName);
    }

    public Page<Product> getProductByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }


    public void updateProduct(Product product, String id, String categoryName, String productName) {
        Optional<Product> findProduct = productRepository.findById(Integer.parseInt(id));
        if (findProduct.isPresent()) {
            Product existingProduct = findProduct.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductDescription(product.getProductDescription());
            productRepository.save(existingProduct);
            productCategoryTree.updateProduct(categoryName, productName, existingProduct);
        }
    }
}

