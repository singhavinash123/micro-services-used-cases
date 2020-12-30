package com.eureka.image.services;

import java.util.List;
import com.eureka.image.entities.Image;

public interface ImageService {
  List<Image> getAllImages();
  Image getImageById(int imageId);
}
