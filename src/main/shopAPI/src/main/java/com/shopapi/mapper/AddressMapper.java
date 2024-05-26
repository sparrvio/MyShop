package com.shopapi.mapper;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper{
    Address convertToEntity(AddressDTO addressDTO);
    AddressDTO convertToDTO(Address address);
}