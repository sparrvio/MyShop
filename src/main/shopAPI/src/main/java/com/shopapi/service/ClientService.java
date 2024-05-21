package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;

import java.util.List;

public interface ClientService {
    Client createClient(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    List<ClientDTO> getAllClients(int page, int size);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getClientByNameAndSurname(String fullName);
    Client updateClient(Client client);
    void deleteClientById(Long id);
    void changeClientAddress(Long id, AddressDTO addressDTO);
}
