package com.shopapi;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapperImpl;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepository;
import com.shopapi.service.AddressService;
import com.shopapi.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) throws ParseException {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        AddressService addressService = context.getBean(AddressService.class);
        Address address = addressService.createAddress();

//        Client client = new Client();
//        ClientMapperImpl clientMapper = context.getBean(ClientMapperImpl.class);
//        ClientDTO clientDTO = clientMapper.toClientDTO(client);

//        System.out.println(clientDTO);
        ClientDTO clientDTO = ClientDTO.builder()
                .clientName("John")
                .clientSurname("Doerrr")
                .birthday(new Date())
                .gender('M')
                .registrationDate(new Date())
                .build();

        ClientService clientService = context.getBean(ClientService.class);
        clientService.createClient(clientDTO);
    }
}

