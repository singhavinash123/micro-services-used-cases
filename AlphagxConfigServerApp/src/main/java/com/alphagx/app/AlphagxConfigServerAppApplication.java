package com.alphagx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AlphagxConfigServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphagxConfigServerAppApplication.class, args);
	}

}
