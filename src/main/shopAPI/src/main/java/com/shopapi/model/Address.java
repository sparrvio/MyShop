package com.shopapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", columnDefinition = "VARCHAR(255)")
    private String country;

    @Column(name = "city", columnDefinition = "VARCHAR(255)")
    private String city;

    @Column(name = "street", columnDefinition = "VARCHAR(255)")
    private String street;
}

