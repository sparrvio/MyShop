package com.shopapi.service;


import com.shopapi.dto.ClientDTO;
import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import com.shopapi.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final AddressRepository addressRepository;


    public Client createClient(ClientDTO clientDTO) {
        Address address = new Address();

        Client client = Client.builder()
                .clientName(clientDTO.getClientName())
                .registrationDate(clientDTO.getRegistrationDate())
                .address_id(address)
                .build();
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).get();
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
