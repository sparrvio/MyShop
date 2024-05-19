package com.shopapi.dto;

import com.shopapi.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


public record ClientDTO(
        String clientName,
        Date registrationDate,
        AddressDTO addressDto)
        implements Serializable {

    public String getClientName() {
        return clientName;
    }


    public Date getRegistrationDate() {
        return registrationDate;
    }


    public AddressDTO addressDto() {
        return addressDto;
    }
}
