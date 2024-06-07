package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client save(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    List<ClientDTO> getAllClients(int page, int size);
    Optional<ClientDTO> getClientById(Long id);
    List<ClientDTO> getClientByNameAndSurname(String fullName);
    Client updateClient(Client client);
    void deleteClientById(Long id);
    void updateAddress(Long id, AddressDTO addressDTO);
}
