package com.shopapi.mapper;

import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
//public interface ProductMapper {
//    ProductDTO toDto(Product product);
//    Product toEntity(ProductDTO productDto);
//}


@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDto);

    default ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setAvailable_stock(product.getAvailable_stock());
        dto.setLast_update_date(product.getLast_update_date());
        Set<ImagesDTO> imagesDto = product.getImages().stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
        dto.setImages(imagesDto);
        return dto;
    }


    // Метод для преобразования Images в ImagesDTO
    default ImagesDTO toDto(Images image) {
        ImagesDTO dto = new ImagesDTO();
        dto.setId(image.getId()); // Предполагая, что у Images есть поле id
        dto.setProductId(image.getProduct_id().getId()); // Получаем id продукта из связанной сущности
        dto.setImageBytes(image.getBytes()); // Предполагая, что у Images есть поле imageBytes
        return dto;
    }
}
