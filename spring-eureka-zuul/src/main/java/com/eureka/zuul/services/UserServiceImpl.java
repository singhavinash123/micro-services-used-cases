package com.eureka.zuul.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.zuul.entity.User;
import com.eureka.zuul.models.UserRequestModel;
import com.eureka.zuul.repository.UserRepository;


/*
 * Service class will have business logic...
 */
@Service
public class UserServiceImpl implements UserService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * one directional encoder, when it will encrypt it can not be decrypt again
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	/*
	 * Writing out business logic for inserting user record into the database..
	 */
	@Override
	public User createUser(UserRequestModel model) {
		User newUser = null;
		if(userRepository.findByEmail(model.getEmail()) != null) {
			return newUser;
		}else {
			newUser = new User();
			String encyptedPassword = bCryptPasswordEncoder.encode(model.getPassword());
			newUser.setEmail(model.getEmail());
			newUser.setPassword(encyptedPassword);
			newUser.setPhone(model.getPhone());
			newUser.setName(model.getName());
			newUser.setUserName(model.getEmail());
			newUser.setUserType("user");
			
			userRepository.save(newUser);
		}

		return newUser;
	}
}

