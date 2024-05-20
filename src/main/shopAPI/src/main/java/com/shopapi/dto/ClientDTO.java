package com.shopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Builder
//@AllArgsConstructor
//@RequiredArgsConstructor
public class ClientDTO {
//    private Long id;
    private String clientName;
    private String clientSurname;
    private Date birthday;
    private char gender;
    private Date registrationDate;
    private String address;
}

