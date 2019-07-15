package com.example.mall.service;

import java.util.List;

import com.example.mall.domain.Order;
import com.example.mall.dto.ItemRequestDTO;
import com.example.mall.dto.ProductDTO;

public interface InventoryService {
	
	public Order createEmptyOrder();
	
	public List<ProductDTO> getProductList();

	/**
	 * @param itemRequest
	 * @return updated Order with requested QTY
	 */
	public Order addItemToOrder(ItemRequestDTO itemRequest);

	/**
	 * @param itemRequest
	 * @return updated order with removed items
	 */
	public Order removeItem(ItemRequestDTO itemRequest);

}
