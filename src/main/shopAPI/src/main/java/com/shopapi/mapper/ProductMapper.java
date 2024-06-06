package com.shopapi.mapper;

import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDto);
}
