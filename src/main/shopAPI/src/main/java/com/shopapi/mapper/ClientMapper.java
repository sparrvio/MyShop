package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

public interface ClientMapper {
    ClientDTO toClientDTO(Client client);
    Client toClient(ClientDTO clientDto);
}
