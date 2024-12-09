package com.example.product_management_system.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryDescription;

}
