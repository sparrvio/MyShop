package com.shopapi.service;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;

import java.util.List;

public interface ClientService {
    public List<ClientDTO> getAllClients();
}
