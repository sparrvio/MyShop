package com.shopapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@ApiModel(description = "Client details")
@Data
@Builder
public class ClientDTO {
    private Long id;
    @Schema(description =  "Name of the client", example  = "John")
    private String clientName;
    @Schema(description  =  "Surname of the client", example  =  "Doe")
    private String clientSurname;
    @Schema(description   =   "birth date of the client", example   =   "1985-01-01")
    private LocalDate birthday;
    @Schema(description    =    "gender of the client", example    =    "M")
    private char gender;
    @Builder.Default
    private LocalDate registrationDate = LocalDate.now();
    private AddressDTO address;
}

