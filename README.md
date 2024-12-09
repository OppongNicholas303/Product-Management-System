# E-Commerce Product Management System

## Overview
The E-Commerce Product Management System is a robust application built using Spring Boot that enables advanced product management capabilities. It incorporates efficient request handling, Spring Data JPA for relational database interactions, Spring Data MongoDB for NoSQL storage, and binary trees for product categorization and retrieval. The application is designed with Spring profiles to support different configurations for development and production environments. An interceptor is implemented to restrict certain operations to admin users only.

## Features

### Spring Boot Setup
- Multi-profile support (Development and Production).
- Spring Boot Actuator enabled for monitoring.
- Port configurations for different profiles (Development: 8081, Production: 8085).

### Request Handling
- Interceptors for logging, authentication, and authorization.
- Authorization via Bearer tokens in the header (Bearer admin required for admin operations).
- Custom error and success response handling.

### Database Integration
- Development Profile: Relational database using Spring Data JPA.
- Production Profile: NoSQL database integration using Spring Data MongoDB.
- CRUD operations for Product and Category entities.

### Binary Tree Integration
- Binary tree structure for efficient product categorization.
- Operations for adding, deleting, and searching products based on categories.

### Pagination and Sorting
- Pagination support for product listings.

## Endpoints

### Authorization
**Admin Restricted Operations:**
To perform operations like adding or updating a product, you must include the following in the request header:
```
Authorization: Bearer admin
```

### Category Management
**Add Category:**
`PUT /api/v1/add-category`
Example Request:
```json
{
  "name": "Electronics"
}
```

### Product Management
**Add Product (Admin Only):**
`PUT /api/v1/product/add-product`
Example Request:
```json
{
  "name": "Television",
  "category": "Electronics",
  "price": 450.0,
  "stock": 20
}
```

**Delete Product:**
`DELETE /api/v1/product/{category}/products/{product}`

**Search Product in Category:**
`GET /api/v1/product/search-product-in-category?category-name=Electronics&product-name=Television`

**Products by Category:**
`GET /api/v1/product/products-by-category`

**Pagination for Products:**
`GET /api/v1/product/find-by-page?page=0&size=10`

**Update Product (Admin Only):**
`PUT /api/v1/product/update-product/{category}/{product-name}/5`
Example Request:
```json
{
  "price": 320.0,
  "stock": 15
}
```

## How to Run

### Development Profile (Default)
1. Clone the repository.
2. Set up a relational database (e.g., MySQL or PostgreSQL).
3. Configure database properties in `application.properties`.
4. Run the application using the command:
   ```bash
   mvn spring-boot:run
   ```
5. Access the application on `http://localhost:8081`.

### Production Profile
1. Set up a NoSQL database (e.g., MongoDB).
2. Configure production properties in `application-prod.properties`.
3. Run the application with the production profile:
   ```bash
   mvn spring-boot:run -Dspring.profiles.active=prod
   ```
4. Access the application on `http://localhost:8085`.

## Limitations
- Full implementation of the production profile is pending. Some operations may not work as expected when running on the production profile.

## Future Enhancements
- Complete the implementation of the production profile.
- Add unit and integration tests for comprehensive validation.
- Improve binary tree functionality to support dynamic balancing for large datasets.

## Technologies Used
- Framework: Spring Boot
- Database: Relational Database (JPA), NoSQL (MongoDB)
- Programming Language: Java
- Libraries/Dependencies: Spring Data JPA, Spring Data MongoDB, Spring Boot Actuator

## Author
Nicholas
