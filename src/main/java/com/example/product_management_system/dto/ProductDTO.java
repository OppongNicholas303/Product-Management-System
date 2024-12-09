package com.example.product_management_system.dto;

import java.math.BigDecimal;

public record ProductDTO(
         String productName,
         String productDescription,
         BigDecimal productPrice,
         String category

) {
}
