package com.eureka.image.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.image.entities.Image;
import com.eureka.image.services.ImageService;

@RestController
@RequestMapping("/")
public class ImageController {
	public static final Logger logger = LoggerFactory.getLogger(ImageController.class);	
	
	@Autowired
	private ImageService imageService;

		
	@RequestMapping(value="/images", method=RequestMethod.GET)
	public List<Image> getAllImages() {
		logger.info("inside ImageController Calling method getAllImages..");
		return imageService.getAllImages();
	}
	
	@RequestMapping(value="/images/{id}", method=RequestMethod.GET)
	public Image getImageById(@PathVariable("id") int imageId) {
		logger.info("inside ImageController Calling method getImageById => {}",imageId);
		return imageService.getImageById(imageId);
	}
}
