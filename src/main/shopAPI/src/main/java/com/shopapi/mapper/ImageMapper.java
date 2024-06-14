package com.shopapi.mapper;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.model.Images;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Images toEntity(ImagesDTO imageDTO);

default ImagesDTO toDTO(Images image) {
        ImagesDTO dto = new ImagesDTO();
        dto.setId(image.getId());
        dto.setProductId(image.getProduct_id().getId());
        dto.setImageBytes(image.getBytes());
        return dto;
    }
}
