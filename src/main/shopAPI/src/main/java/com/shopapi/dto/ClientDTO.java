package com.shopapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


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

