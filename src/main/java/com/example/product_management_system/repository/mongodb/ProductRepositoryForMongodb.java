package com.example.product_management_system.repository.mongodb;

import com.example.product_management_system.model.mangodb.ProductForMongodb;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Profile("prod")
public interface ProductRepositoryForMongodb extends MongoRepository<ProductForMongodb, ObjectId> {
    Optional<ProductForMongodb> findByProductName(String productName);
    void deleteByProductName(String productName);
}