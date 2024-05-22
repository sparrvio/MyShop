package com.shopapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {
//    private Long id;
    private String clientName;
    private String clientSurname;
    private String birthday;
    private char gender;
    private String registrationDate;
    private String address;
}

