//package com.example.product_management_system.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MongoConnectionTest implements CommandLineRunner {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            if (mongoTemplate.getDb() != null) {
//                System.out.println("Successfully connected to MongoDB database: " + mongoTemplate.getDb().getName());
//            } else {
//                System.out.println("Failed to connect to MongoDB.");
//            }
//        } catch (Exception e) {
//            System.out.println("Error connecting to MongoDB: " + e.getMessage());
//        }
//    }
//}
