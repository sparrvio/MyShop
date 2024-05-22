package com.shopapi.controller;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import com.shopapi.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/client/search/{fullName}")
    public ResponseEntity<List<ClientDTO>> getClientByFullName(@PathVariable String fullName) {
        List<ClientDTO> clientDTO = clientService.getClientByNameAndSurname(fullName);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @GetMapping("/allClients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientDTO = clientService.getAllClients();
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @GetMapping("/allClientsWithParams")
    public ResponseEntity<List<ClientDTO>> getAllClients(@RequestParam int page, @RequestParam int size) {
        List<ClientDTO> clientDTO = clientService.getAllClients(page, size);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<?> updateAddress(
            @PathVariable Long id, @RequestBody AddressDTO newAddress) {
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }


//    @GetMapping("/clients")
//    public List<ClientDTO> getClients(){
//        List<ClientDTO> allClients = clientService.;
//        return allClients;
//    }
}
