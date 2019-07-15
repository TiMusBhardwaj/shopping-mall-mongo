package com.example.mall.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseDocument {
	
	@Id
	private String id;

	@CreatedDate
	private Date created_on;

	@LastModifiedDate
	private Date updated_on;
	
	@Version
	private Long version;
	
	
	
	
	
}
