package com.shopapi.dto;

import com.shopapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String country;
    private String city;
    private String street;

    public static AddressDTO of(Address address) {
        return new AddressDTO(address.getId(), address.getCountry(), address.getCity(), address.getStreet());
    }
}
