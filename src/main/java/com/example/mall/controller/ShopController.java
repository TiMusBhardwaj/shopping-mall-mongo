package com.example.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.domain.Order;
import com.example.mall.dto.ItemRequestDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.service.InventoryService;



@RestController
@Validated
public class ShopController {
	
	@Autowired
	private InventoryService service;
	
	@PostMapping(path="/shop/order")
	public Order getEmptyCart() {

		return service.createEmptyOrder();
		
	}
	
	@GetMapping(path="/shop/product")
	public List<ProductDTO> getAllProducts(){
		return service.getProductList();
	}
	
	//TODO: We can use session to manage order_cart
	//TODO: put validation on qty like min and max value
	@PostMapping(path="/shop/Item")
	public Order addItem(@RequestBody ItemRequestDTO itemRequest) {
		return service.addItemToOrder(itemRequest);
		
	}
	

	
}
