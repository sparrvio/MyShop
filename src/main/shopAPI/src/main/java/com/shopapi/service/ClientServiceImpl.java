package com.shopapi.service;


import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    @Autowired
    private final ClientRepository clientRepository;
    @Override
    @Transactional
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.getAllClients();
        return clients.stream()
                .map(ClientDTO::of)
                .collect(Collectors.toList());
    }
}
