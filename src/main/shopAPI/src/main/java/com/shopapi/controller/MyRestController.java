package com.shopapi.controller;

import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import com.shopapi.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/client")
    public ResponseEntity<ClientDTO> getClient(){
        ClientDTO client = clientService.getClientById(5L);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO){
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.OK);
    }

//    @GetMapping("/clients")
//    public List<ClientDTO> getClients(){
//        List<ClientDTO> allClients = clientService.;
//        return allClients;
//    }
}
