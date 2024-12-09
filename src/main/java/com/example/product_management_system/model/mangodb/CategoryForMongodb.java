package com.example.product_management_system.model.mangodb;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class CategoryForMongodb {
    @Id
    private ObjectId categoryId;

    private String categoryName;

    @DBRef
    @JsonManagedReference
    private List<ProductForMongodb> productsForMongodb;

    // Convert ObjectId to String for REST responses
    public String getCategoryIdString() {
        return categoryId != null ? categoryId.toString() : null;
    }

    // Convert String to ObjectId when receiving IDs in requests
    public void setCategoryIdString(String id) {
        this.categoryId = id != null ? new ObjectId(id) : null;
    }
}