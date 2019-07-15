package com.example.mall.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.mall.controller.Exception.CartInactiveException;
import com.example.mall.controller.Exception.ProductUnavailableException;
import com.example.mall.domain.Order;
import com.example.mall.domain.OrderStatus;
import com.example.mall.domain.Product;
import com.example.mall.dto.ItemRequestDTO;
import com.example.mall.dto.ProductDTO;
import com.example.mall.repo.mongo.OrderRepo;
import com.example.mall.repo.mongo.ProductRepo;

@Repository
public class InventoryServiceImpl implements InventoryService {

	

	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Order createEmptyOrder() {
		Order order = Order.createEmptyOrder();
		return orderRepo.save(order);
	}

	// TODO: pagination should be used here
	// TODO: handle mapping errors
	@Override
	public List<ProductDTO> getProductList() {
		List<Product> product = productRepo.findAll();
		return product.stream().map(x -> mapper.map(x, ProductDTO.class)).collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.mall.service.InventoryService#addItemToOrder(com.example.mall.dto
	 * .ItemRequestDTO)
	 */
	/*
	 * 
	 * 
	 * 
	*/
	@Override
	@Transactional
	public Order addItemToOrder(ItemRequestDTO itemRequest) {
		Optional<Order> orderOp = orderRepo.findById(itemRequest.getOrderId());
		if (orderOp.get() == null || orderOp.get().getStatus() != OrderStatus.ACTIVE) {
			throw CartInactiveException.createWith(itemRequest.getOrderId());
		}

		// TODO: Can send available count information back, so that user can revise qty
		Optional<Product> productOp = productRepo.findById(itemRequest.getProductId());
		if (productOp.get() == null || productOp.get().getAvailableCount() < itemRequest.getQty()) {
			throw ProductUnavailableException.createWith(itemRequest.getProductId());
		}

		// Update Inventory
		Product product = productOp.get();
		product.setAvailableCount(product.getAvailableCount() - itemRequest.getQty());
		product.addInProgressItem(itemRequest.getOrderId(), itemRequest.getQty());
		
		
		
		
		// Save May throw optimistic locking exception 
		//whole transaction should be reverted in that case
		
		productRepo.save(product);

		// update Order_cart
		Order order = orderOp.get();
		order.addItem(product.getId(), itemRequest.getQty(), 
				product.getName(), product.getCategory());
		
		// Save May throw optimistic locking exception 
		//whole transaction should be reverted in that case
		return orderRepo.save(order);
		

	}
	
	
	
	
	@Override
	@Transactional
	public Order removeItem(ItemRequestDTO itemRequest) {
		Optional<Order> orderOp = orderRepo.findById(itemRequest.getOrderId());
		if (orderOp.get() == null || orderOp.get().getStatus() != OrderStatus.ACTIVE) {
			throw CartInactiveException.createWith(itemRequest.getOrderId());
		}
		
		Order order = orderOp.get();
		
		// TODO: Can send available count information back, so that user can revise qty
		Optional<Product> productOp = productRepo.findById(itemRequest.getProductId());
		if (productOp.get() == null ) {
			throw ProductUnavailableException.createWith(itemRequest.getProductId());
		}

		// Update Inventory
		Product product = productOp.get();
		product.setAvailableCount(product.getAvailableCount() + itemRequest.getQty());
		product.removeItem(itemRequest.getOrderId(), itemRequest.getQty());
		
		
		
		
		// Save May throw optimistic locking exception 
		//whole transaction should be reverted in that case
		
		productRepo.save(product);

		// update Order_cart
		
		order.removeItem(product.getId(), itemRequest.getQty(), 
				product.getName(), product.getCategory());
		
		// Save May throw optimistic locking exception 
		//whole transaction should be reverted in that case
		return orderRepo.save(order);
		

	}
	
	
	
	/**
	 * @param accountId
	 * @param orderCartId
	 * @return
	 * 
	 * 
	 * TODO:: 
	 * Use Custom Spring Repo here with Mongo Template
	 *  1. Change Status of Cart To PAYMENT_IN_PROGESS (ATOMIC OPERATION)
	 *  2. Calculate Amount and deduct from user account.(Atomic operation)
	 *  3. 
	 *  4. Update Status of  cart to Transaction complete. Atomic
	 *  
	 */
	public Order checkout(String accountId, String orderCartId) {
		return null;
		
		

	}

}
