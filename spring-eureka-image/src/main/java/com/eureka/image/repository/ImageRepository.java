package com.eureka.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.image.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
