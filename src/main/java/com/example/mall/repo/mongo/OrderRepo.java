package com.example.mall.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mall.domain.Order;

public interface OrderRepo extends MongoRepository<Order, String>{
	
	

}
