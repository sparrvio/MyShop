package com.shopapi.dto;

import com.shopapi.model.Supplier;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@ApiModel(description = "Product details")
@Data
@Builder
public class ProductDTO {
    private Long id;
    @Schema(description =  "Name of the client", example  = "Apple")
    private String name;
    @Schema(description  = "Category of the product", example = "Fruit")
    private String category;
    @Schema(description = "Price of the product", example =  "10.0")
    private Double price;
    @Schema(description = "Available stock of the product", example  = "10")
    private Long available_stock;

    private LocalDate last_update_date;
    private Supplier supplier_id;
    private Set<ImagesDTO> images;
}
