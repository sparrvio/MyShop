package com.shopapi.mapper;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
//@Component
public class AddressMapperImpl implements AddressMapper{
    @Override
    public Address toAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new IllegalArgumentException("AddressDTO cannot be null");
        }
        Address address = Address.builder()
                .country(addressDTO.getCountry())
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .build();
        return address;
    }

    @Override
    public AddressDTO toAddressDTO(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        AddressDTO addressDTO = AddressDTO.builder()
//                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
        return addressDTO;
    }
}
