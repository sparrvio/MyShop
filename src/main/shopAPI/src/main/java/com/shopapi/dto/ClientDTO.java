package com.shopapi.dto;

import com.shopapi.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String clientName;
    private String clientSurname;
    private Date birthday;
    private char gender;
    private Date registrationDate; // Note: Consider using LocalDate instead of Date for better handling of dates
    private AddressDTO address;

    public static ClientDTO of (Client client) {
        return new ClientDTO(client.getId(), client.getClientName(), client.getClientSurname(),
                client.getBirthday(), client.getGender(), client.getRegistrationDate(),
                AddressDTO.of(client.getAddress_id()));
    }
}
