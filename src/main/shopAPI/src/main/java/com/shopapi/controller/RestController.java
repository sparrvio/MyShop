package com.shopapi.controller;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.service.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
        if(clientDTO.isEmpty()) {
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

    @PostMapping("/client/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<String> createClient(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam char gender) {
        if (gender!= 'M' && gender!= 'F') {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthDate, inputFormatter);

        ClientDTO clientDTO = ClientDTO.builder()
                .clientName(name)
                .clientSurname(surname)
                .birthday(date)
                .gender(gender)
                .build();

        clientService.createClient(clientDTO);
        return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
    }

//    @PostMapping("/client/create") // для отправки данных через Postman в теле запроса (POST) json объект
//    public ResponseEntity<String> createClient(@RequestBody ClientDTO clientDTO) {
//        if (!Arrays.asList('M', 'F').contains(clientDTO.getGender())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate date = LocalDate.parse(clientDTO.getBirthday().toString(), inputFormatter);
//            clientDTO.setRegistrationDate(LocalDate.now());
//
//            clientService.createClient(clientDTO);
//            return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
//        } catch (DateTimeParseException e) {
//            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/client/{id}/address")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressDTO newAddress) {
        Optional<ClientDTO> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/client/delete") //удаление клиента по id через GET запрос
    public ResponseEntity<?> delete(@RequestParam @Valid Long idDelete) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
        if(clientDTO.isEmpty())  {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClientById(idDelete);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }

//    @DeleteMapping("/client/delete/{idDelete}")  //для Postman удаление клиента через DELETE запрос
//    public ResponseEntity<?> delete(@PathVariable Long idDelete)  {
//        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
//        if(clientDTO.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        clientService.deleteClientById(idDelete);
//        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
//    }
}
