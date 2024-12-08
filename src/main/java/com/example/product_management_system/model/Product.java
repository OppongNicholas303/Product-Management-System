package com.example.product_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    @JsonBackReference
    private Category category;
}