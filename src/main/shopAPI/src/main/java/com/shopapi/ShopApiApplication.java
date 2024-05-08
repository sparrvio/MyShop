package com.shopapi;

import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepository;
import com.shopapi.repository.ClientRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApiApplication.class, args);
        Client client = new Client();
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        clientRepository.getAllClients();
    }

}
