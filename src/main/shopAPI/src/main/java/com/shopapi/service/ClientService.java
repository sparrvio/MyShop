package com.shopapi.service;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;

import java.util.List;

public interface ClientService {
    public Client createClient(ClientDTO clientDTO);
//    public List<ClientDTO> getAllClients();
}
