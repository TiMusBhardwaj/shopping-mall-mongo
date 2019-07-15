package com.example.mall.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.mall.controller.Exception.InvalidArgumentException;

import lombok.Getter;
import lombok.Setter;

@Document(collection="oders")
@Getter
@Setter
//TODO: NEED to include price details
public class Order extends BaseDocument{
	
	public static Order createEmptyOrder() {
		Order order = new Order();
		order.status = OrderStatus.ACTIVE;
		return order;
	}
	
	private OrderStatus status;
	private Map<String, Items> itemListMap= new HashMap<>();
	
	/**
	 * @param id
	 * @param qty
	 * @param name
	 * @param category
	 */
	public void addItem(String id, long qty, String name, String category) {
		
		itemListMap.putIfAbsent(id, new Items(id, 0, name, category));
		itemListMap.get(id).setQuantity(itemListMap.get(id).getQuantity()+qty);
	}
	
	/**
	 * @param id
	 * @param qty
	 * @param name
	 * @param category
	 */
	public void removeItem(String id, long qty, String name, String category) {
		if (itemListMap.get(id) == null || itemListMap.get(id).getQuantity() < qty) {
			InvalidArgumentException.createWith("Cannot Remove QTY "+ qty+ " for Product :"+ name);
		}
			
		itemListMap.get(id).setQuantity(itemListMap.get(id).getQuantity()-qty);
	}
	
}


