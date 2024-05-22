package com.shopapi.controller;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Client;
import com.shopapi.service.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class RestController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/client/id")
    public ResponseEntity<?> getClient(@RequestParam @Valid Long clientID) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(clientID);
        if(!clientDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(clientDTO.get(), HttpStatus.OK);
    }

    @GetMapping("/client/search")
    public ResponseEntity<List<ClientDTO>> getClientByFullName(@RequestParam String fullName) {
        List<ClientDTO> clientDTOs = clientService.getClientByNameAndSurname(fullName);
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }


    @GetMapping("/client/allClients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientDTO = clientService.getAllClients();
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @GetMapping("/client/allClientsWithParams")
    public ResponseEntity<List<ClientDTO>> getAllClients(@RequestParam int page, @RequestParam int size) {
        List<ClientDTO> clientDTO = clientService.getAllClients(page, size);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping("/client/create")
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.OK);
    }

    @PutMapping("/client/{id}/address")
    public ResponseEntity<?> updateAddress(
            @PathVariable Long id, @RequestBody AddressDTO newAddress) {
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }
}
