package com.shopapi.dto;

import com.shopapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;


public record AddressDTO(
        String country,
        String city,
        String street)
        implements Serializable {

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }
}

