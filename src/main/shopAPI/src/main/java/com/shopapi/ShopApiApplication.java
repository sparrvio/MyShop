package com.shopapi;

import com.shopapi.config.OpenApiConfig;
import com.shopapi.dto.ClientDTO;
import com.shopapi.dto.ImagesDTO;
import com.shopapi.dto.ProductDTO;

import com.shopapi.mapper.ProductMapper;
import com.shopapi.model.Address;
import com.shopapi.model.Images;
import com.shopapi.model.Product;
import com.shopapi.model.Supplier;
import com.shopapi.repository.ProductRepository;
import com.shopapi.repository.SupplierRepository;
import com.shopapi.service.ClientService;
import com.shopapi.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Sql(scripts = "/schema.sql")

@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        OpenApiConfig openApiConfig  = context.getBean(OpenApiConfig.class);
        openApiConfig.publicApi();

        ProductMapper productMapper  = context.getBean(ProductMapper.class);
        ClientService clientService = context.getBean(ClientService.class);
        clientService.deleteClientById(22L);

        ClientDTO client1 = ClientDTO.builder()
                .clientName("John")
                .clientSurname("Doe")
                .birthday(LocalDate.of(1985, 2, 15))
                .gender('M')
                .registrationDate(LocalDate.of(2002, 10, 15))
                .build();
        clientService.save(client1);

        ClientDTO client2 = ClientDTO.builder()
                .clientName("Jane")
                .clientSurname("Smith")
                .birthday(LocalDate.of(1990, 6, 20))
                .gender('F')
                .registrationDate(LocalDate.of(2010, 7, 30))
                .build();

        ClientDTO client3 = ClientDTO.builder()
                .clientName("Michael")
                .clientSurname("Johnson")
                .birthday(LocalDate.of(1987, 11, 25))
                .gender('M')
                .registrationDate(LocalDate.of(2006, 12, 15))
                .build();


        clientService.save(client1);
        clientService.save(client2);
        clientService.save(client3);

        Address address = Address.builder()
                        .country("Russia")
                        .city("Klin")
                        .street("Lenina")
                        .build();

        Address address2 = Address.builder()
                .country("Russia")
                .city("Klin")
                .street("Lenina")
                .build();

        Supplier supplier1 = Supplier.builder()
                .name("Mango")
                .address_id(address)
                .phone_number("+7(999)999-99-99")
                .build();

        Supplier supplier2  = Supplier.builder()
                .name("Banana")
                .address_id(address2)
                .phone_number("+7(999)999-99-99")
                .build();


        ProductDTO productDTO1  = ProductDTO.builder()
                .name("Apple")
                .category("Fruits")
                .price(1090.0)
                .available_stock(100L)
                .build();


        ProductDTO productDTO2   = ProductDTO.builder()
                .name("Banana")
                .category("Fruit")
                .price(10.0)
                .available_stock(1L)
                .build();

    }
}

