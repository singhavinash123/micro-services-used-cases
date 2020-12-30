package com.eureka.gallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.gallery.models.Gallery;
import com.eureka.gallery.models.Image;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/")
public class GalleryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);
	
	private final String getAllImagesURL = "http://image-service/images/";

	
//	@Value("${image.service.url}")
//	private String getAllImagesURL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value="/get-all" , method = RequestMethod.GET)
	public Gallery getAllGallery() {
		LOGGER.info("Inside GalleryController calling method getAllGallery ... ");
		LOGGER.info("getAllImagesURL => {}" , getAllImagesURL);

		Gallery gallery = new Gallery();
		List<Object> images = restTemplate.getForObject(getAllImagesURL, List.class);
		gallery.setImages(images);
		return gallery;
	}
  
	@RequestMapping(value="/{id}" , method = RequestMethod.GET)
	public Image getGalleryById(@PathVariable final int id) {
		LOGGER.info("Inside GalleryController calling method getGalleryById => {}" , id);
		LOGGER.info("getAllImagesURL => {}" , getAllImagesURL);

		Image images = restTemplate.getForObject(getAllImagesURL+""+id, Image.class);
		LOGGER.info("get the images object => {}", images);
		return images;
	}
	
	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin() {
		return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
	}
	
	// a fallback method to be called if failure happened
	public Gallery fallback(Throwable hystrixCommand) {
		return new Gallery(false, null);
	}
}