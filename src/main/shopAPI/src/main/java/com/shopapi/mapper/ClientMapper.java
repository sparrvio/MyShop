package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface ClientMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "address_id", target = "address")
    ClientDTO convertToDTO(Client client);

    @Mapping(source = "address", target = "address_id")
    Client convertToEntity(ClientDTO clientDTO);
}