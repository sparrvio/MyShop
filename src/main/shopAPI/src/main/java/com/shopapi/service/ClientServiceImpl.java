package com.shopapi.service;


import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;
    @Override
    @Transactional
    public List<Client> getAllClients() {
        return null;
    }
}
