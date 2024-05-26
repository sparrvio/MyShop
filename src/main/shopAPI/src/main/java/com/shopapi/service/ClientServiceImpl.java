package com.shopapi.service;


import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapper;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import com.shopapi.repository.AddressRepository;
import com.shopapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Data
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;
    private final AddressService addressService;

    public Client createClient(ClientDTO clientDTO) {
        Client client = clientMapper.convertToEntity(clientDTO);
        Address address = new Address();
        addressRepository.save(address);
        client.setAddress_id(address);
        client.setBirthday(clientDTO.getBirthday());
        client.setRegistrationDate(clientDTO.getRegistrationDate());
        return clientRepository.save(client);
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ClientDTO> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")); // По умолчанию сортировка по возрастанию id
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.getContent().stream()
                .map(clientMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClientDTO> getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            return Optional.empty();
        }
        Optional<ClientDTO> clientDTO = Optional.ofNullable(clientMapper.convertToDTO(client.get()));
        return clientDTO;

//        if (clientID == null || clientID <= 0) {
//            // Если ID не был предоставлен или равно 0, возвращаем ошибку
//            return new ResponseEntity<>("ID клиента не может быть пустым или равно 0.", HttpStatus.BAD_REQUEST);
//        }
//
//        ClientDTO client = clientService.getClientById(clientID);
//        if (client == null) {
//            // Если клиент не найден, возвращаем сообщение об ошибке
//            return new ResponseEntity<>("Клиент с указанным ID не найден.", HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @Override
    public List<ClientDTO> getClientByNameAndSurname(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length == 2) {
            String clientName = parts[0];
            String clientSurname = parts[1];
            List<Client> clients = clientRepository.findByClientNameAndClientSurname(clientName, clientSurname);
            return clients.stream()
                    .map(clientMapper::convertToDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Client updateClient(Client client) {
        if (getClientById(client.getId()) == null) {
            Address address = new Address();
            addressRepository.save(address);
            client.setAddress_id(address);
            return clientRepository.save(client);
        } else {
            Client oldClient = clientRepository.findById(client.getId()).get();
            oldClient.setClientName(client.getClientName());
            oldClient.setClientSurname(client.getClientSurname());
            oldClient.setGender(client.getGender());
            oldClient.setBirthday(client.getBirthday());
            oldClient.setRegistrationDate(client.getRegistrationDate());
            return oldClient;
        }
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void updateAddress(Long id, AddressDTO addressDTO) {
        addressService.updateAddress(id, addressDTO);
    }
}
