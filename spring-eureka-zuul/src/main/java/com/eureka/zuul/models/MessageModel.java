package com.eureka.zuul.models;

import lombok.Data;

@Data
public class MessageModel {
	private String message;
	
	public MessageModel(String message) {
		this.message = message;
	}
}
