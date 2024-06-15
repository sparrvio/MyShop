package com.shopapi.mapper;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDto);

    default ProductDTO toDto(Product product) {
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .available_stock(product.getAvailable_stock())
                .last_update_date(product.getLast_update_date())
                .images(product.getImages().stream()
                        .map(this::toDto)
                        .collect(Collectors.toSet()))
                .build();

        return dto;
    }

    default ImagesDTO toDto(Images image) {
        ImagesDTO dto = new ImagesDTO();
        dto.setId(image.getId());
        dto.setProductId(image.getProduct_id().getId());
        dto.setImageBytes(image.getBytes());
        return dto;
    }
}
