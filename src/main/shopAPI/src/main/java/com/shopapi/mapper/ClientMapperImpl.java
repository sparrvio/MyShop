package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
//@Component
public class ClientMapperImpl implements ClientMapper{
    @Override
    public ClientDTO toClientDTO(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        ClientDTO clientDTO = ClientDTO.builder()
                .clientName(client.getClientName())
                .clientSurname(client.getClientSurname())
                .birthday(client.getBirthday())
                .gender(client.getGender())
                .registrationDate(client.getRegistrationDate())
//                .address(client.getAddress_id().getId().toString() + " " +
//                        client.getAddress_id().getCity() + " " +
//                        client.getAddress_id().getCountry() + " " +
//                        client.getAddress_id().getStreet())
                .build();

        return clientDTO;
    }

    @Override
    public Client toClient(ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new IllegalArgumentException("ClientDTO cannot be null");
        }
        Address address = new Address();
        Client client = Client.builder()
                .clientName(clientDTO.getClientName())
                .clientSurname(clientDTO.getClientSurname())
                .birthday(clientDTO.getBirthday())
                .gender(clientDTO.getGender())
                .registrationDate(clientDTO.getRegistrationDate())
                .build();
        client.setAddress_id(address);
        return client;
    }
}
