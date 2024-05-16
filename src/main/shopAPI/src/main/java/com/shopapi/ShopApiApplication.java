package com.shopapi;

import com.shopapi.model.Client;
import com.shopapi.repository.ClientRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShopApiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        ClientRepositoryImpl clientRepository = context.getBean(ClientRepositoryImpl.class);
        clientRepository.getAllClients();
    }
}

