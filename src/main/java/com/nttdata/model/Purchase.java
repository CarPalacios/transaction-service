package com.nttdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nttdata.model.Purchase;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "purchase")
@Data
public class Purchase {
	
	@Id
	private String id;

	@Field(name = "cardNumber")
	private String cardNumber;

	@Field(name = "amount")
	private Double amount;

}