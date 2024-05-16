package com.shopapi.controller;

import com.shopapi.dto.ClientDTO;
import com.shopapi.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getClients(){
        List<ClientDTO> allClients = clientService.getAllClients();
        return allClients;
    }
}
