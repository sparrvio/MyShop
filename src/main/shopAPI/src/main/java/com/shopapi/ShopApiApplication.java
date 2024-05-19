package com.shopapi;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
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
        AddressDTO addressDTO = new AddressDTO("Street", "City", "Country");
        Address address = addressService.createAddress(addressDTO);
        System.out.println(address);


//        ClientService clientService = context.getBean(ClientService.class);
//        ClientDTO clientDTO = new ClientDTO("Name", new Date(), addressDTO);
//        Client client = clientService.createClient(clientDTO);
//        System.out.println(client);
    }
}

