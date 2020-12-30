package com.eureka.zuul.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.zuul.entity.User;
import com.eureka.zuul.models.AuthRequest;
import com.eureka.zuul.models.ErrorResponseModel;
import com.eureka.zuul.models.MessageModel;
import com.eureka.zuul.models.TokenResponseModel;
import com.eureka.zuul.models.UserRequestModel;
import com.eureka.zuul.services.UserServiceImpl;
import com.eureka.zuul.utils.JwtUtil;

@RestController
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/")
	public String test(){
		return "hello world";
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest){
		logger.info("INSIDE THE LoginController AND generateToken  METHOD!!");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authRequest.getEmail(),
							authRequest.getPassword())
					);
		} catch (Exception ex) {
			ErrorResponseModel errorResponse = new ErrorResponseModel();
			errorResponse.setError(new MessageModel("user.auth.failed"));
			errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		TokenResponseModel tokenModel = new TokenResponseModel("user.auth.success",
				jwtUtil.generateToken(authRequest.getEmail())
				);
		return new ResponseEntity<>(tokenModel, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody UserRequestModel requestModel) {
		logger.info("{}",requestModel);
		User user = userServiceImpl.createUser(requestModel);
		if(user != null) {
			// user created!!
			MessageModel model = new MessageModel("user.created.success");
			return new ResponseEntity<>(model, HttpStatus.CREATED);
		}else {
			// user exists!!!
			ErrorResponseModel errorResponse = new ErrorResponseModel();
			errorResponse.setError(new MessageModel("user.already.exists"));
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
