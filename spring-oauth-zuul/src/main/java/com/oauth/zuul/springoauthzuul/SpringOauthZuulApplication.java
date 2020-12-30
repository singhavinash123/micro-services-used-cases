package com.oauth.zuul.springoauthzuul;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso

@RestController
public class SpringOauthZuulApplication {

	@GetMapping("/")
	public String getMessage(Principal principle){
		return "Hi, "+principle.getName()+" welcome!!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringOauthZuulApplication.class, args);
	}

}
