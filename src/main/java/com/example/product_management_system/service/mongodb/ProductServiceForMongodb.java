package com.example.product_management_system.service.mongodb;

import com.example.product_management_system.Mapper.Mapping;
import com.example.product_management_system.dto.ProductDTO;
import com.example.product_management_system.exception.AlreadyExist;
import com.example.product_management_system.model.mangodb.ProductCategoryTreeMongodb;
import com.example.product_management_system.model.mangodb.ProductForMongodb;
import com.example.product_management_system.repository.mongodb.ProductRepositoryForMongodb;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Profile("prod")
public class ProductServiceForMongodb {
    private ProductRepositoryForMongodb productRepository;
    private ProductCategoryTreeMongodb productCategoryTree;

    public ProductServiceForMongodb(
            ProductRepositoryForMongodb productRepository
            , ProductCategoryTreeMongodb productCategoryTree)
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
            ProductForMongodb product = productMapping.toProductForMongo(productDTO);
            productRepository.save(product);
            // productCategoryTree.addProduct();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<ProductForMongodb> searchProductInCategory(String categoryName, String productName) {
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

    public  List<ProductForMongodb> getProductsByCategory(String categoryName) {
        return productCategoryTree.getProductInCategory(categoryName);
    }


}
