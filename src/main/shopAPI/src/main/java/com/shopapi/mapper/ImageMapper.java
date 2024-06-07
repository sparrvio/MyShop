package com.shopapi.mapper;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.model.Images;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Images toModel(ImagesDTO imageDTO);
    ImagesDTO toDTO(Images image);
}
