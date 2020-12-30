package com.eureka.image.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.image.entities.Image;
import com.eureka.image.repository.ImageRepository;

@Service
public class ImageServiceIMPL implements ImageService{

	@Autowired
	private ImageRepository repository;

	@Override
	public List<Image> getAllImages() {
		return repository.findAll();
	}

	@Override
	public Image getImageById(int imageId) {
		return repository.findById(imageId).orElse(null);
	}

}
