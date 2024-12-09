package com.example.product_management_system.model.mangodb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class ProductForMongodb {
    @Id
    private ObjectId productId;

    @Indexed(unique = true)
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    @DBRef
    @JsonBackReference
    private CategoryForMongodb category;

    // Convert ObjectId to String for REST responses
    public String getCategoryIdString() {
        return productId != null ? productId.toString() : null;
    }

    // Convert String to ObjectId when receiving IDs in requests
    public void setCategoryIdString(String id) {
        this.productId = id != null ? new ObjectId(id) : null;
    }
}
