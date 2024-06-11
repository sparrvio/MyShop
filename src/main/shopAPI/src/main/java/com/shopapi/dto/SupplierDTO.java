package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Supplier details")
public class SupplierDTO {
    @Schema(description = "Unique identifier of the supplier", example = "1")
    private Long id;
    @Schema(description =  "Name of the client", example  = "Apple")
    private String supplierName;
    @Schema(description  =  "address of the supplier", example  =  "address")
    private AddressDTO supplierAddress;
    @Schema(description  =  "phone of the supplier", example  =  "+7-999-999-99-99")
    private String supplierPhone;
    @Schema(description  =  "products of the supplier", example  ="product")
    private Set<ProductDTO> products;
}
