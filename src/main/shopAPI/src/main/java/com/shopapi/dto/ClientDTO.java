package com.shopapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class ClientDTO {
//    private Long id;
    private String clientName;
    private String clientSurname;
    private LocalDate birthday;
    private char gender;
    private LocalDate registrationDate;
    private AddressDTO address;
}

