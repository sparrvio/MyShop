package com.shopapi.controller;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Client API", version = "v1"))
@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Get client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/client/id")
    public ResponseEntity<?> getClient(@RequestParam @Valid Long clientID) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(clientID);
        if (clientDTO.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientDTO.get(), HttpStatus.OK);
    }

    @Operation(summary = "Get client by full name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/client/search")
    public ResponseEntity<?> getClientByFullName(@RequestParam String fullName) {
        List<ClientDTO> clientDTOs = clientService.getClientByNameAndSurname(fullName);
        if (clientDTOs.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all clients with pagination parameters", response = ClientDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/client/allClients")
    public ResponseEntity<?> getAllClients(@RequestParam(required = false) Integer limit,
                                           @RequestParam(required = false) Integer offset) {
        if((limit != null && limit < 0) || (offset != null && offset < 1)){
            return new ResponseEntity<>("Bad request - invalid page or size parameters",
                    HttpStatus.BAD_REQUEST);
        }
        List<ClientDTO> clientDTO;
        if (limit!= null && offset!= null) {
            clientDTO = clientService.getAllClients(limit, offset);
        } else {
            clientDTO = clientService.getAllClients();
        }
        if (clientDTO.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping("/client/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<String> createClient(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam char gender) {
        if (gender != 'M' && gender != 'F') {
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

    @PostMapping("/client/createClientForPostman")
    // для отправки данных через Postman в теле запроса (POST) json объект
    public ResponseEntity<String> createClientForPostman(@RequestBody ClientDTO clientDTO) {
        if (!Arrays.asList('M', 'F').contains(clientDTO.getGender())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(clientDTO.getBirthday().toString(), inputFormatter);
            clientDTO.setRegistrationDate(LocalDate.now());

            clientService.createClient(clientDTO);
            return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
        }
    }

    // для отправки данных через HTML форму в теле запроса. Да, я знаю, что здесь должен быть другой тип запроса, но я пока не знаю JavaScript
    @GetMapping("/client/updateAddressForHtml")
    public ResponseEntity<?> updateAddressForHtml(@RequestParam Long id, @RequestParam String country, @RequestParam String city, @RequestParam String street) {
        System.out.println("id" + id);
        Optional<ClientDTO> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AddressDTO newAddress = AddressDTO.builder()
                .country(country)
                .city(city)
                .street(street)
                .build();
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @PutMapping("/client/{id}/address") // для отправки данных через Postman в теле запроса (PUT) json объект
    public ResponseEntity<?> updateAddressForPostman(@PathVariable Long id, @RequestBody AddressDTO newAddress) {
        Optional<ClientDTO> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  Да, я знаю, что здесь должен быть другой тип запроса, но я пока не знаю JavaScript
    @GetMapping(value = "/client/delete")
    //для отправки данных через HTML форму в теле запроса. Удаление клиента по id через GET запрос
    public ResponseEntity<?> delete(@RequestParam @Valid Long idDelete) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
        if (clientDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClientById(idDelete);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/client/deleteForPostman/{idDelete}")  //для Postman удаление клиента через DELETE запрос
    public ResponseEntity<?> deleteForPostman(@PathVariable Long idDelete) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
        if (clientDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteClientById(idDelete);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }
}
