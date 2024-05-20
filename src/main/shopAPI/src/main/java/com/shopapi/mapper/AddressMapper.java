package com.shopapi.mapper;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);
    AddressDTO toAddressDTO(Address address);
}