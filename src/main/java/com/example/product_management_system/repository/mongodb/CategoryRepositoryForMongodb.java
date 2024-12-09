package com.example.product_management_system.repository.mongodb;

import com.example.product_management_system.model.mangodb.CategoryForMongodb;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


@Profile("prod")
public interface CategoryRepositoryForMongodb extends MongoRepository<CategoryForMongodb, ObjectId> {
    List<CategoryForMongodb> findByCategoryName(String categoryName);
}
