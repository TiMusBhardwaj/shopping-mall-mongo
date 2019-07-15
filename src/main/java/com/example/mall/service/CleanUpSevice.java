package com.example.mall.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.mall.domain.Order;
import com.example.mall.domain.OrderStatus;
import com.example.mall.repo.mongo.OrderRepo;
import com.example.mall.repo.mongo.ProductRepo;

@Service
public class CleanUpSevice {
	
	@Autowired
	private MongoTemplate template;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Scheduled(fixedDelay=15000)
	public void expireBuckets() {
		
		// Change Order Status from Active To UNDER_EXPIRE_FLOW for which threshold is reached.
		initiateExpiration();
		
		//Add Qty back to Inventory
		adjustExpiredQty();
		//set bucket as Expired
		setExpired();
		
	}
	
	
	/**
	 * 
	 * TODO
	 * Check for all active bucket product count for each product.
	 * Match it with on Inventory and Adjust discrepencies.
	 * 
	 * 
	 */
	@Scheduled(fixedDelay=24*3600*100)
	public void AuditProduct() {
		
		
		
	}
	
	
	/**
	 * TODO: This Method will remove add the expiring QTY back to inventory
	 * INCOMPLETE
	 */
	public void adjustExpiredQty() {
		//TODO: DO This in a single query so as to avoid optimistic locking exception
		
	}
	
	public void initiateExpiration() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -3);
		Date threeMinBack = cal.getTime();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("updated_on").lt(threeMinBack).and("status").is(OrderStatus.ACTIVE));
		Update update = new Update().set("status", OrderStatus.UNDER_EXPIRE_FLOW);
		template.updateMulti(query, update, Order.class);
		
	}
	
	
	public void setExpired() {
	
		
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(OrderStatus.UNDER_EXPIRE_FLOW));
		Update update = new Update().set("status", OrderStatus.EXPIRED);
		template.updateMulti(query, update, Order.class);
		
	}
	
}
