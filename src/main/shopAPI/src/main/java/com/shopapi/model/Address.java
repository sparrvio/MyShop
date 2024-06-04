package com.shopapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", length = 255, columnDefinition = "VARCHAR(255)")
    private String country;

    @Column(name = "city", length = 255, columnDefinition = "VARCHAR(255)")
    private String city;

    @Column(name = "street", length = 255, columnDefinition = "VARCHAR(255)")
    private String street;
}

