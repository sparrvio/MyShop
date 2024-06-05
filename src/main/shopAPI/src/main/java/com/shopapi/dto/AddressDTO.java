package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Address details")
public class AddressDTO {
    @Schema(description  =  "country of the client", example  =  "Russia")
    private String country;
    @Schema(description  =  "city of the client", example  =  "Moscow")
    private String city;
    @Schema(description = "street of the client", example  =  "Moscow street")
    private String street;
}

