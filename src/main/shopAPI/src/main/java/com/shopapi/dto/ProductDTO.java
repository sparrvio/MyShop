package com.shopapi.dto;

import com.shopapi.model.Supplier;
import io.swagger.annotations.ApiModel;
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
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer available_stock;
    private LocalDate last_update_date;
    private Supplier supplier_id;
}
