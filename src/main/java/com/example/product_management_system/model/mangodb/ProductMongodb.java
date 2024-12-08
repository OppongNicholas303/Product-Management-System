package com.example.product_management_system.model.mangodb;

import com.example.product_management_system.model.mongodb.CategoryMongodb;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class ProductMongodb {
    @Id
    private String id;  // MongoDB auto-generated or managed by the sequence generator

    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    @DBRef
    @JsonBackReference
    private CategoryMongodb category;

}
