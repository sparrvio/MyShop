package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface ClientMapper extends Mapper{
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "clientName", target = "clientName")
    @Mapping(source = "clientSurname", target = "clientSurname")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "registrationDate", target = "registrationDate")
    @Mapping(source = "address_id", target = "address")
//    @Mapping(source = "address_id", target = "address", qualifiedByName = "mapAddressToString")
    ClientDTO clientToClientDto(Client client);

//    @InheritInverseConfiguration
//    Client clientDtoToClient(ClientDTO clientDto);

}
