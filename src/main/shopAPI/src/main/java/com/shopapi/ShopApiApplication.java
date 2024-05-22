package com.shopapi;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapperImpl;
import com.shopapi.model.Client;
import com.shopapi.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.jdbc.Sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@Sql(scripts = "/schema.sql")
@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);

        ClientService clientService = context.getBean(ClientService.class);
        ClientDTO clientDTO = ClientDTO.builder()
                .clientName("John")
                .clientSurname("Doer")
                .birthday("12-12-1990")
                .gender('M')
                .registrationDate("10-01-2002")
                .build();

        Client client = clientService.createClient(clientDTO);
        ClientMapperImpl clientMapper = context.getBean(ClientMapperImpl.class);

        List<ClientDTO> list = clientService.getAllClients();
        list.forEach(System.out::println);


        AddressDTO addressDTO = AddressDTO.builder()
                .country("Russia")
                .city("Saratov")
                .street("Lenina")
                .build();
        clientService.updateAddress(2L, addressDTO);
        list.forEach(System.out::println);

        Client client2 = Client.builder()
                .id(2L)
                .clientName("Petr")
                .clientSurname("Markov")
                .birthday(new Date())
                .gender('M')
                .registrationDate(new Date())
                .build();
        clientService.updateClient(client2);

        clientService.getClientByNameAndSurname("Bill Doer")
                .stream()
                .forEach(System.out::println);
    }
}

