package com.shopapi.service;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;

import java.util.List;

public interface ClientService {
    Client createClient(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);

    Client updateClient(Client client);

    void deleteClientById(Long id);
}
