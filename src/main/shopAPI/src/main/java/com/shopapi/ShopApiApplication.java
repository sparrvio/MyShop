package com.shopapi;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepositoryImpl;
import com.shopapi.service.AddressServiceIml;
import com.shopapi.service.ClientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        ClientRepositoryImpl clientRepository = context.getBean(ClientRepositoryImpl.class);
        List<Client> list = clientRepository.getAllClients();
        list.stream()
                .map(ClientDTO::of)
                .forEach(System.out::println);
        Client client = list.get(0);
        AddressServiceIml addressServiceIml = context.getBean(AddressServiceIml.class);
        System.out.println(addressServiceIml.getAddressesByClientId(client));
    }
}

