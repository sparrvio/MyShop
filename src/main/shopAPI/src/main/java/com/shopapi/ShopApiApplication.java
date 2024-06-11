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
                .clientName("Johnааа")
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

        ClientDTO client4 = ClientDTO.builder()
                .clientName("Emily")
                .clientSurname("Williams")
                .birthday(LocalDate.of(1995, 8, 14))
                .gender('F')
                .registrationDate(LocalDate.of(2015, 9, 24))
                .build();

        ClientDTO client5 = ClientDTO.builder()
                .clientName("Robert")
                .clientSurname("Brown")
                .birthday(LocalDate.of(1992, 3, 29))
                .gender('M')
                .registrationDate(LocalDate.of(2012, 4, 18))
                .build();

        ClientDTO client6 = ClientDTO.builder()
                .clientName("Jessica")
                .clientSurname("Jones")
                .birthday(LocalDate.of(1989, 12, 19))
                .gender('F')
                .registrationDate(LocalDate.of(2009, 1, 28))
                .build();

        ClientDTO client7 = ClientDTO.builder()
                .clientName("James")
                .clientSurname("Davis")
                .birthday(LocalDate.of(1986, 5, 13))
                .gender('M')
                .registrationDate(LocalDate.of(2005, 6, 23))
                .build();

        ClientDTO client8 = ClientDTO.builder()
                .clientName("Patricia")
                .clientSurname("Miller")
                .birthday(LocalDate.of(1993, 1, 27))
                .gender('F')
                .registrationDate(LocalDate.of(2013, 2, 16))
                .build();

        ClientDTO client9 = ClientDTO.builder()
                .clientName("William")
                .clientSurname("Wilson")
                .birthday(LocalDate.of(1994, 9, 21))
                .gender('M')
                .registrationDate(LocalDate.of(2014, 10, 31))
                .build();


        clientService.save(client1);
        clientService.save(client2);
        clientService.save(client3);
        clientService.save(client4);
        clientService.save(client5);
        clientService.save(client6);
        clientService.save(client7);
        clientService.save(client8);
        clientService.save(client9);


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

        Set<ImagesDTO> images = new HashSet<>();// удалить !!! заменить !!!
//        product1.setImages(images);  // удалить !!! заменить !!!
//        product1.setSupplier_id(supplier);// удалить !!! заменить !!!

        ProductDTO productDTO1  = ProductDTO.builder()
                .name("Apple")
                .category("Fruits")
                .price(1090.0)
                .supplier_id(supplier1)
                .available_stock(100)
                .images(images)
                .build();

        productDTO1.setImages(images);

        ProductDTO productDTO2   = ProductDTO.builder()
                .name("Banana")
                .category("Fruit")
                .price(10.0)
                .supplier_id(supplier2)
                .available_stock(1)
                .build();

        SupplierRepository supplierRepository = context.getBean(SupplierRepository.class);
        supplierRepository.save(supplier1);
        supplierRepository.save(supplier2);
        productDTO1.setSupplier_id(supplier1);
        productDTO2.setSupplier_id(supplier2);
        ProductServiceImpl productServiceImpl = context.getBean(ProductServiceImpl.class);
        System.out.println(supplier1);
        System.out.println(productDTO1);
        productServiceImpl.save(productDTO1);
//        productServiceImpl.save(productDTO2);

        ProductRepository productRepository  = context.getBean(ProductRepository.class);
        Product product1   = productRepository.findById(1L).get();


        Product product = productMapper.toEntity(productDTO1);
        System.out.println(product.getImages());

//
//        List<ClientDTO> list = clientService.getAllClients();
//        list.forEach(System.out::println);
//
//
//        ClientMapper clientMapper = context.getBean(ClientMapper.class);
//

//        clientService.updateAddress(7L, addressDTO);
//        AddressMapper addressMapper = context.getBean(AddressMapper.class);

//        Optional<ClientDTO> client = clientService.getClientById(1L);



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

