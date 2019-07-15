package com.example.mall.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mall.domain.Product;

public interface ProductRepo extends MongoRepository<Product, String> {

}
