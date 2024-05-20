package com.shopapi.service;


import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapper;
import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import com.shopapi.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {
//    @Autowired
    private final ClientRepository clientRepository;
//    @Autowired
    private final AddressRepository addressRepository;
//    @Autowired
    private final ClientMapper clientMapper;

//    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository, ClientMapper clientMapper) {
//        this.clientRepository = clientRepository;
//        this.addressRepository = addressRepository;
//        this.clientMapper = clientMapper;
//    }


    public Client createClient(ClientDTO clientDTO) {

        Client client = clientMapper.toClient(clientDTO);
        Address address = new Address();
        addressRepository.save(address);
        client.setAddress_id(address);
//        Client savedClient = clientRepository.save(client);
//        address.setId(savedClient.getAddress_id().getId());
//        savedClient.setAddress_id(address);
//        client.setAddress_id(address);
        return clientRepository.save(client);
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toClientDTO)
                .collect(Collectors.toList());
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
