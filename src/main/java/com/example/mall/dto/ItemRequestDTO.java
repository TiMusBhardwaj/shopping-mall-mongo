package com.example.mall.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class ItemRequestDTO {
	
	@NotEmpty
	private String orderId;
	@NotEmpty
	private String productId;
	@Min(1)
	@Max(5)
	private long qty;

}
