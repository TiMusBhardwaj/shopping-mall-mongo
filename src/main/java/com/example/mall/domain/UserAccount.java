package com.example.mall.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Document(collection="user_account")
@Getter
@Setter
public class UserAccount extends BaseDocument{
	
	private String name;
	private BigDecimal balance;
	private String bankName;

}
