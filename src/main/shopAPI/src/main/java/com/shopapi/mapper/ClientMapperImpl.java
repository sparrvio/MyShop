package com.shopapi.mapper;

import com.shopapi.dto.ClientDTO;
import com.shopapi.exception.CustomParseException;
import com.shopapi.model.Client;
import org.mapstruct.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public class ClientMapperImpl implements ClientMapper{

    @Override
    public ClientDTO toClientDTO(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedBirthday = dateFormat.format(client.getBirthday());
        String formattedRegistrationDate = dateFormat.format(client.getRegistrationDate());

        ClientDTO clientDTO = ClientDTO.builder()
                .clientName(client.getClientName())
                .clientSurname(client.getClientSurname())
                .birthday(formattedBirthday)
                .gender(client.getGender())
                .registrationDate(formattedRegistrationDate)
                .address(client.getAddress_id().toString())
                .build();

        return clientDTO;
    }

    @Override
    public Client toClient(ClientDTO clientDTO) {
        if (clientDTO == null) {
            throw new IllegalArgumentException("ClientDTO cannot be null");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date birthday = null;
        Date registrationDate = null;
        try {
            birthday = dateFormat.parse(clientDTO.getBirthday());
            registrationDate = dateFormat.parse(clientDTO.getRegistrationDate());
        } catch (ParseException e) {
            throw new CustomParseException(e);
        }
        Client client = Client.builder()
                .clientName(clientDTO.getClientName())
                .clientSurname(clientDTO.getClientSurname())
                .birthday(birthday)
                .gender(clientDTO.getGender())
                .registrationDate(registrationDate)
                .build();
        return client; // Клиеет возвращается без поля address_id
    }
}