package com.shopapi;

import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.ClientMapperImpl;
import com.shopapi.model.Client;
import com.shopapi.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);

        ClientDTO clientDTO = ClientDTO.builder()
                .clientName("Johnnnn")
                .clientSurname("Doerrr")
                .birthday(new Date())
                .gender('M')
                .registrationDate(new Date())
                .build();

        ClientService clientService = context.getBean(ClientService.class);
        Client client = clientService.createClient(clientDTO);
        ClientMapperImpl clientMapper = context.getBean(ClientMapperImpl.class);
        ClientDTO clientDTO2 = clientMapper.toClientDTO(client);
        System.out.println(clientDTO2);
        List<ClientDTO> list = clientService.getAllClients();
        list.forEach(System.out::println);
        clientService.deleteClientById(2L);


    }
}

