package com.example.product_management_system.model;


import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String productName;
    private String productDescription;
    private Integer productPrice;

}
