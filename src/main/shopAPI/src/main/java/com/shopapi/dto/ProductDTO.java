package com.shopapi.dto;

import com.shopapi.model.Supplier;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Product details")
public class ProductDTO {
    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;
    @Schema(description =  "Name of the client", example  = "Apple")
    private String name;
    @Schema(description  = "Category of the product", example = "Fruit")
    private String category;
    @Schema(description = "Price of the product", example =  "10.0")
    private Double price;
    @Schema(description = "Available stock of the product", example  = "10")
    private Integer available_stock;
    @Schema(description = "Date of the last update of the product", example =  "2020-01-01")
    private LocalDate last_update_date;
    @Schema(description  =  "Supplier ID of the product", example  =  "1")
    private Supplier supplier_id;
}
