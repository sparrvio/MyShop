package com.shopapi.service;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.mapper.ImageMapper;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import com.shopapi.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Transactional
@Data
@Component
@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imagesRepository;
    private ImageMapper imageMapper;
    private ProductService productService;
    private ProductMapper productMapper;

    @Override
    public Optional<ImagesDTO> getImages(Long imageId) {
        Optional<Images> imageOptional = imagesRepository.findById(imageId);
        if (imageOptional.isEmpty()) {
            return Optional.empty();
        }
        Images images = imageOptional.get();
        ImagesDTO imagesDTO = imageMapper.toDTO(images);
        return Optional.of(imagesDTO);
    }

    @Override
    public Optional<ImagesDTO> getImagesByProductID(Long productId) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteImage(Long imageId) {
        Images image = imagesRepository.findById(imageId).get();
        Product product = productMapper.toEntity(productService.getById(image.getProduct_id().getId()).get());
        product.getImages().removeIf(images -> images.getId().equals(imageId));
        try {
            productService.save(productMapper.toDto(product));
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("This is bag");
        }
        imagesRepository.deleteById(imageId);
    }

    @Override
    public Images saveImagesByProductID(byte[] image, Long productId) {
        Images images = new Images();
        images.setBytes(image);
        Optional<ProductDTO> productDTO = productService.getById(productId);
        if (productDTO.isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        Product product = productMapper.toEntity(productDTO.get());
        images.setProduct_id(product);
        return imagesRepository.save(images);
    }

    @Override
    public void saveImagesByImageID(byte[] image, Long image_id) {
        imagesRepository.findById(image_id).orElseThrow().setBytes(image);
    }
}
