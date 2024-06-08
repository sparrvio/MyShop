package com.shopapi.service;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.model.Images;

import java.awt.*;
import java.util.Optional;

public interface ImageService {
    Optional<ImagesDTO> getImages(Long imageId);
    Optional<ImagesDTO> getImagesByProductID(Long productId);
    void deleteImage(Long imageId);
    Images saveImages(byte[] image, Long productId);

}
