package com.shopapi;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ClientDTO;
import com.shopapi.mapper.AddressMapper;
import com.shopapi.mapper.ClientMapper;

import com.shopapi.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Optional;

//@Sql(scripts = "/schema.sql")
@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);

        ClientService clientService = context.getBean(ClientService.class);
        clientService.deleteClientById(22L);

//        ClientDTO client1 = ClientDTO.builder()
//                .clientName("Johnааа")
//                .clientSurname("Doe")
//                .birthday(LocalDate.of(1985, 2, 15))
//                .gender('M')
//                .registrationDate(LocalDate.of(2002, 10, 15))
//                .build();
//        clientService.createClient(client1);

//        ClientDTO client2 = ClientDTO.builder()
//                .clientName("Jane")
//                .clientSurname("Smith")
//                .birthday(LocalDate.of(1990, 6, 20))
//                .gender('F')
//                .registrationDate(LocalDate.of(2010, 7, 30))
//                .build();
//
//        ClientDTO client3 = ClientDTO.builder()
//                .clientName("Michael")
//                .clientSurname("Johnson")
//                .birthday(LocalDate.of(1987, 11, 25))
//                .gender('M')
//                .registrationDate(LocalDate.of(2006, 12, 15))
//                .build();
//
//        ClientDTO client4 = ClientDTO.builder()
//                .clientName("Emily")
//                .clientSurname("Williams")
//                .birthday(LocalDate.of(1995, 8, 14))
//                .gender('F')
//                .registrationDate(LocalDate.of(2015, 9, 24))
//                .build();
//
//        ClientDTO client5 = ClientDTO.builder()
//                .clientName("Robert")
//                .clientSurname("Brown")
//                .birthday(LocalDate.of(1992, 3, 29))
//                .gender('M')
//                .registrationDate(LocalDate.of(2012, 4, 18))
//                .build();
//
//        ClientDTO client6 = ClientDTO.builder()
//                .clientName("Jessica")
//                .clientSurname("Jones")
//                .birthday(LocalDate.of(1989, 12, 19))
//                .gender('F')
//                .registrationDate(LocalDate.of(2009, 1, 28))
//                .build();
//
//        ClientDTO client7 = ClientDTO.builder()
//                .clientName("James")
//                .clientSurname("Davis")
//                .birthday(LocalDate.of(1986, 5, 13))
//                .gender('M')
//                .registrationDate(LocalDate.of(2005, 6, 23))
//                .build();
//
//        ClientDTO client8 = ClientDTO.builder()
//                .clientName("Patricia")
//                .clientSurname("Miller")
//                .birthday(LocalDate.of(1993, 1, 27))
//                .gender('F')
//                .registrationDate(LocalDate.of(2013, 2, 16))
//                .build();
//
//        ClientDTO client9 = ClientDTO.builder()
//                .clientName("William")
//                .clientSurname("Wilson")
//                .birthday(LocalDate.of(1994, 9, 21))
//                .gender('M')
//                .registrationDate(LocalDate.of(2014, 10, 31))
//                .build();


//        clientService.createClient(client1);
//        clientService.createClient(client2);
//        clientService.createClient(client3);
//        clientService.createClient(client4);
//        clientService.createClient(client5);
//        clientService.createClient(client6);
//        clientService.createClient(client7);
//        clientService.createClient(client8);
//        clientService.createClient(client9);


//
//        List<ClientDTO> list = clientService.getAllClients();
//        list.forEach(System.out::println);
//
//
        ClientMapper clientMapper = context.getBean(ClientMapper.class);
//
        AddressDTO addressDTO = AddressDTO.builder()
                .country("Russia")
                .city("Klin")
                .street("Lenina")
                .build();
//        clientService.updateAddress(7L, addressDTO);
        AddressMapper addressMapper = context.getBean(AddressMapper.class);

        Optional<ClientDTO> client = clientService.getClientById(1L);



//        list.forEach(System.out::println);

//        Client client2 = Client.builder()
//                .id(2L)
//                .clientName("Petr")
//                .clientSurname("Markov")
//                .birthday(new Date())
//                .gender('M')
//                .registrationDate(new Date())
//                .build();
//        clientService.updateClient(client2);

//        clientService.getClientByNameAndSurname("Bill Doer")
//                .stream()
//                .forEach(System.out::println);
    }
}

