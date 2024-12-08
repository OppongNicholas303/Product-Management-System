package com.example.product_management_system.model.mongodb;

import com.example.product_management_system.model.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class CategoryMongodb {
    @Id
    private String id;

    private String categoryName;

    @Field("products")
    @JsonManagedReference
    private List<Product> products;

}
