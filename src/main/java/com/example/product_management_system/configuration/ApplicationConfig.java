package com.example.product_management_system.configuration;

import com.example.product_management_system.model.ProductCategoryTree;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    ProductCategoryTree productCategoryTree(){
        return new ProductCategoryTree();
    }

}
