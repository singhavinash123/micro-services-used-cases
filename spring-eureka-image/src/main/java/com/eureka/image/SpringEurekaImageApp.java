package com.eureka.image;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.eureka.image.entities.Image;
import com.eureka.image.repository.ImageRepository;

@SpringBootApplication
@EnableEurekaClient 	
public class SpringEurekaImageApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaImageApp.class, args);
	}
	
	@Autowired
	private ImageRepository repository;
	
	@PostConstruct
	public void initUsers() {
		List<Image> users = Stream.of(
				new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
				new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
				new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112")
				).collect(Collectors.toList());
		repository.saveAll(users);
	}
}
