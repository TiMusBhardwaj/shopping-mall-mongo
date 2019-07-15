package com.example.mall.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.mall.controller.Exception.InvalidArgumentException;

import lombok.Getter;
import lombok.Setter;

@Document(collection="inventory")
@Getter
@Setter
public class Product extends BaseDocument{
	
	@Indexed(unique=true)
	private String name;
	private long availableCount;
	
	@Indexed(unique=false)
	private String category;
	private String Description;
	
	private Map<String, CurrentOrders> inProgressOrder = new HashMap<>();
	
	public void addInProgressItem(String orderId, long qty) {
		inProgressOrder.putIfAbsent(orderId, new CurrentOrders(orderId, qty));
		inProgressOrder.get(orderId).setQty(inProgressOrder.get(orderId).getQty()+qty);
	}
	
	public void removeItem(String orderId, long qty) {
		if (inProgressOrder.get(orderId) == null || inProgressOrder.get(orderId).getQty() < qty) {
			InvalidArgumentException.createWith("Cannot Remove QTY "+ qty+ " for Product :"+ name);
		}
		inProgressOrder.get(orderId).setQty(inProgressOrder.get(orderId).getQty()-qty);
	}
	
		

}
