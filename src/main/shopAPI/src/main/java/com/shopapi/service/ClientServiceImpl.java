package com.shopapi.service;


import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapper;
import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import com.shopapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;


    public Client createClient(ClientDTO clientDTO) {

        Client client = clientMapper.toClient(clientDTO);
        Address address = new Address();
        addressRepository.save(address);
        client.setAddress_id(address);
        return clientRepository.save(client);
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        ClientDTO clientDTO = null;
        try {
            Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
            clientDTO = clientMapper.toClientDTO(client);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return clientDTO;
    }

    @Override
    public List<ClientDTO> getClientByNameAndSurname(String fullName) {
        String [] parts = fullName.split(" ");
       if (parts.length == 2) {
           String clientName = parts[0];
           String clientSurname = parts[1];
           List<Client> clients = clientRepository.findByClientNameAndClientSurname(clientName, clientSurname);
           return clients.stream()
                   .map(clientMapper::toClientDTO)
                   .collect(Collectors.toList());
       }
       return Collections.emptyList();
    }

    public Client updateClient(Client client) {
        if (getClientById(client.getId()) == null) {
            Address address = new Address();
            addressRepository.save(address);
            client.setAddress_id(address);
            return clientRepository.save(client);
        } else {
            Client oldClient = clientRepository.findById(client.getId()).get();
            oldClient.setClientName(client.getClientName());
            oldClient.setClientSurname(client.getClientSurname());
            oldClient.setGender(client.getGender());
            oldClient.setBirthday(client.getBirthday());
            oldClient.setRegistrationDate(client.getRegistrationDate());
            return clientRepository.save(oldClient);
        }
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
