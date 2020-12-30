package com.eureka.gallery.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Gallery {
	private Boolean result = true;
	private List<Object> images;
	
	public Gallery() {
	}

}
