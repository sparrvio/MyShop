package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO convertToDTO(Client client);

    Client convertToEntity(ClientDTO clientDTO);
}