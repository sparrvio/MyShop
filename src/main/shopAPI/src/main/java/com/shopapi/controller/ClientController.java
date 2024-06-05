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
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/client/id")
    public ResponseEntity<?> getClient(@RequestParam(required = false) Long clientID) {
        if (clientID == null || clientID < 1) {
            return new ResponseEntity<>("Invalid client ID", HttpStatus.BAD_REQUEST);
        }
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

    @Operation(summary = "Retrieve all clients with pagination parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
            @ApiResponse(responseCode = "400", description = "Bad request - invalid page or size parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/client/allClients")
    public ResponseEntity<?> getAllClients(@RequestParam(required = false) Integer limit,
                                           @RequestParam(required = false) Integer offset) {
        if ((limit != null && limit < 0) || (offset != null && offset < 1)) {
            return new ResponseEntity<>("Bad request - invalid page or size parameters",
                    HttpStatus.BAD_REQUEST);
        }
        List<ClientDTO> clientDTO;
        if (limit != null && offset != null) {
            clientDTO = clientService.getAllClients(limit, offset);
        } else {
            clientDTO = clientService.getAllClients();
        }
        if (clientDTO.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @Operation(summary = "Create new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Client created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/client/create") // для отправки данных через HTML форму в теле запроса (POST)
    public ResponseEntity<String> createClient(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam char gender) {
        if (gender != 'M' && gender != 'F') {
            return new ResponseEntity<>("Invalid client gender", HttpStatus.BAD_REQUEST);
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

    @Operation(summary =  "Create new client for postman")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "222", description = "Client created successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request - invalid client data or gender"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping("/client/createClientForPostman") // для отправки данных в теле запроса json объект
    public ResponseEntity<String> createClientForPostman(@RequestBody ClientDTO clientDTO) {
        if (!Arrays.asList('M', 'F').contains(clientDTO.getGender())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            clientDTO.setRegistrationDate(LocalDate.now());
            clientService.createClient(clientDTO);
            return new ResponseEntity<>("Data received successfully", HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Invalid date format", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary  =  "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/client/updateAddressForHtml") // PUT через GET для отправки данных через HTML форму в теле запроса
    public ResponseEntity<?> updateAddressForHtml(@RequestParam Long id, @RequestParam String country, @RequestParam String city, @RequestParam String street) {
        Optional<ClientDTO> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        AddressDTO newAddress = AddressDTO.builder()
                .country(country)
                .city(city)
                .street(street)
                .build();
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @Operation(summary  =  "Update address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @PutMapping("/client/{id}/address") // для отправки данных в теле запроса (PUT) json объект
    public ResponseEntity<?> updateAddressForPostman(@PathVariable Long id, @RequestBody AddressDTO newAddress) {
        Optional<ClientDTO> client = clientService.getClientById(id);
        if (client.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        clientService.updateAddress(id, newAddress);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @Operation(summary  =  "Delete client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping(value = "/client/delete")
    //DELETE через GET  отправка данных через HTML форму в теле запроса. Удаление клиента по id через GET запрос
    public ResponseEntity<?> delete(@RequestParam @Valid Long idDelete) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
        if (clientDTO.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        clientService.deleteClientById(idDelete);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }

    @Operation(summary = "Delete for Postman client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @DeleteMapping("/client/deleteForPostman/{idDelete}")  // удаление клиента через DELETE запрос

    public ResponseEntity<?> deleteForPostman(@PathVariable Long idDelete) {
        Optional<ClientDTO> clientDTO = clientService.getClientById(idDelete);
        if (clientDTO.isEmpty()) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        clientService.deleteClientById(idDelete);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }
}
