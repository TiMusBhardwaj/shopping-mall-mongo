package com.example.mall.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentOrders {
	
	private String orderId;
	private long qty;

}
