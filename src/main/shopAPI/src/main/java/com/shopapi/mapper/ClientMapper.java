package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Named("mapAddressToString")
    default String mapAddressToString(Address address) {
        return "Hello";
//        if (address!= null) {
//            return address.toString(); // Используйте любую логику преобразования, например, address.toString()
//        } else {
//            return null;
//        }
    }
    @Mapping(source = "clientName", target = "clientName")
    @Mapping(source = "clientSurname", target = "clientSurname")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "registrationDate", target = "registrationDate")
//    @Mapping(source = "address_id", target = "address", qualifiedByName = "mapAddressToString")
//    @Mapping(source = "address_id", target = "address", qualifiedByName = "mapAddressToString")
    ClientDTO clientToClientDto(Client client);

    @InheritInverseConfiguration
    Client clientDtoToClient(ClientDTO clientDto);

}
