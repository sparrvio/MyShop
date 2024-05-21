package com.shopapi.dto;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class ClientDTO {
//    private Long id;
    private String clientName;
    private String clientSurname;
    private String birthday;
    private char gender;
    private Date registrationDate;
    private String address;
}

