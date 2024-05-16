package com.shopapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String country = "RUSSIA";

    @Column(name = "city", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String city = "MOSCOW";

    @Column(name = "street", length = 255, columnDefinition = "VARCHAR(255)")
    private String street;

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Address() {
    }
}

