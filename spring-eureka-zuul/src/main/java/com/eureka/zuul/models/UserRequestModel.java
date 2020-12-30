package com.eureka.zuul.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestModel {
	private String name;
	private String email;
	private String password;
	private String phone;
}
