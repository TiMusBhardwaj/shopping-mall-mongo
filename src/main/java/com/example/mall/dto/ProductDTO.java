package com.example.mall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	private String id;
	private String name;
	private long availableCount;
	private String category;
	private String Description;

}
