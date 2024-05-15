package com.shopapi;

import com.shopapi.repository.ClientRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShopApiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        ClientRepositoryImpl clientRepository = context.getBean(ClientRepositoryImpl.class);
        clientRepository.getAllClients();
    }
}
