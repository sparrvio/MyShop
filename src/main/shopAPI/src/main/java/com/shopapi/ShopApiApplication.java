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
        ClientDTO client1 = ClientDTO.builder()
                .clientName("John")
                .clientSurname("Doe")
                .birthday("12-12-1990")
                .gender('M')
                .registrationDate("10-01-2002")
                .build();

        ClientDTO client2 = ClientDTO.builder()
                .clientName("Jane")
                .clientSurname("Smith")
                .birthday("15-05-1985")
                .gender('F')
                .registrationDate("20-02-2010")
                .build();

        ClientDTO client3 = ClientDTO.builder()
                .clientName("Michael")
                .clientSurname("Johnson")
                .birthday("25-11-1975")
                .gender('M')
                .registrationDate("30-03-2004")
                .build();

        ClientDTO client4 = ClientDTO.builder()
                .clientName("Emily")
                .clientSurname("Williams")
                .birthday("04-08-1992")
                .gender('F')
                .registrationDate("05-06-2011")
                .build();

        ClientDTO client5 = ClientDTO.builder()
                .clientName("Robert")
                .clientSurname("Brown")
                .birthday("14-09-1960")
                .gender('M')
                .registrationDate("16-07-2007")
                .build();

        ClientDTO client6 = ClientDTO.builder()
                .clientName("Linda")
                .clientSurname("Jones")
                .birthday("22-10-1987")
                .gender('F')
                .registrationDate("27-08-2009")
                .build();

        ClientDTO client7 = ClientDTO.builder()
                .clientName("James")
                .clientSurname("Miller")
                .birthday("31-01-1955")
                .gender('M')
                .registrationDate("01-05-2006")
                .build();

        ClientDTO client8 = ClientDTO.builder()
                .clientName("Patricia")
                .clientSurname("Davis")
                .birthday("19-02-1942")
                .gender('F')
                .registrationDate("15-01-2005")
                .build();

        ClientDTO client9 = ClientDTO.builder()
                .clientName("William")
                .clientSurname("Wilson")
                .birthday("28-03-1931")
                .gender('M')
                .registrationDate("17-02-2003")
                .build();

        ClientDTO client10 = ClientDTO.builder()
                .clientName("Mary")
                .clientSurname("Anderson")
                .birthday("07-04-1920")
                .gender('F')
                .registrationDate("24-03-2002")
                .build();

        clientService.createClient(client1);
        clientService.createClient(client2);
        clientService.createClient(client3);
        clientService.createClient(client4);
        clientService.createClient(client5);
        clientService.createClient(client6);
        clientService.createClient(client7);
        clientService.createClient(client8);
        clientService.createClient(client9);
        clientService.createClient(client10);

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

//        Client client2 = Client.builder()
//                .id(2L)
//                .clientName("Petr")
//                .clientSurname("Markov")
//                .birthday(new Date())
//                .gender('M')
//                .registrationDate(new Date())
//                .build();
//        clientService.updateClient(client2);

        clientService.getClientByNameAndSurname("Bill Doer")
                .stream()
                .forEach(System.out::println);
    }
}

