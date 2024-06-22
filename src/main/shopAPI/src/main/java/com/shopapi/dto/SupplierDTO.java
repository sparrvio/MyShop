package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@ApiModel(description = "Supplier details")
@Data
@Builder
public class SupplierDTO {
    private Long id;
    @Schema(description =  "Name of the supplier", example  = "Apple")
    private String supplierName;
    private AddressDTO supplierAddress;
    @Schema(description  =  "phone of the supplier", example  =  "+7-999-999-99-99")
    private String supplierPhone;
    private Set<ProductDTO> products;
}
