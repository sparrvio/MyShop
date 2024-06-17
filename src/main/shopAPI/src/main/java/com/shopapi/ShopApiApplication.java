package com.shopapi;

import com.shopapi.config.OpenApiConfig;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ShopApiApplication {
    public static void main(String[] args) {
        try {
            String flywayConfigLocation = "classpath:/db/migration";

            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:postgresql://localhost:5432/lightShopApi", "postgres", "postgres")
                    .locations(flywayConfigLocation)
                    .load();

            flyway.migrate();

            System.out.println("Миграции успешно применены.");
        } catch (Exception e) {
            System.err.println("Ошибка при применении миграций: " + e.getMessage());
            e.printStackTrace();
        }

        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
        OpenApiConfig openApiConfig = context.getBean(OpenApiConfig.class);
        openApiConfig.publicApi();
    }
//    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(ShopApiApplication.class, args);
//        OpenApiConfig openApiConfig  = context.getBean(OpenApiConfig.class);
//        openApiConfig.publicApi();

//        ProductMapper productMapper  = context.getBean(ProductMapper.class);
//        ProductService productService   = context.getBean(ProductServiceImpl.class);
//        ClientService clientService = context.getBean(ClientService.class);
//        SupplierMapper supplierMapper= context.getBean(SupplierMapper.class);
//        SupplierService supplierService = context.getBean(SupplierService.class);
//
//        clientService.deleteClientById(22L);
//
//        ClientDTO client1 = ClientDTO.builder()
//                .clientName("Иван")
//                .clientSurname("Петров")
//                .birthday(LocalDate.of(1985, 2, 15))
//                .gender('M')
//                .registrationDate(LocalDate.of(2002, 10, 15))
//                .build();
//        clientService.save(client1);
//
//        ClientDTO client2 = ClientDTO.builder()
//                .clientName("Ирина")
//                .clientSurname("Смирнова")
//                .birthday(LocalDate.of(1990, 6, 20))
//                .gender('F')
//                .registrationDate(LocalDate.of(2010, 7, 30))
//                .build();
//
//        ClientDTO client3 = ClientDTO.builder()
//                .clientName("Петр")
//                .clientSurname("Сергеев")
//                .birthday(LocalDate.of(1987, 11, 25))
//                .gender('M')
//                .registrationDate(LocalDate.of(2006, 12, 15))
//                .build();
//
//
//        clientService.save(client1);
//        clientService.save(client2);
//        clientService.save(client3);
//
//        Address address = Address.builder()
//                        .country("Россия")
//                        .city("Клин")
//                        .street("Московская")
//                        .build();
//
//        Address address2 = Address.builder()
//                .country("Россия")
//                .city("Звенигород")
//                .street("Ленина")
//                .build();
//
//        Supplier supplier1 = Supplier.builder()
//                .name("ФруктОпт")
//                .address_id(address)
//                .phone_number("+7(999)999-99-99")
//                .build();
//
//        Supplier supplier2  = Supplier.builder()
//                .name("ОвощиСейл")
//                .address_id(address2)
//                .phone_number("+7(999)999-99-99")
//                .build();
//
//
//        supplierService.save(supplierMapper.toDTO(supplier1));
//        supplierService.save(supplierMapper.toDTO(supplier2));
//
//        ProductDTO productDTO1  = ProductDTO.builder()
//                .name("Яблоко")
//                .category("Фрукты")
//                .price(1090.0)
//                .available_stock(100L)
//                .build();
//
//        ProductDTO productDTO2   = ProductDTO.builder()
//                .name("Бананы")
//                .category("Фрукты")
//                .price(10.0)
//                .available_stock(200L)
//                .build();
//
//        ProductDTO productDTO3   = ProductDTO.builder()
//                .name("Картофель")
//                .category("Овощи")
//                .price(22.7)
//                .available_stock(300L)
//                .build();
//        productService.save(productDTO1);
//        productService.save(productDTO2);
//        productService.save(productDTO3);
//    }
}

