package com.eureka.zuul.services;

import com.eureka.zuul.entity.User;
import com.eureka.zuul.models.UserRequestModel;

public interface UserService {
	User createUser(UserRequestModel model);
}
