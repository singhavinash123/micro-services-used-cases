package com.eureka.zuul.models;


import lombok.Data;

@Data
public class ErrorResponseModel {
	private MessageModel error;
	private int status;
}
	