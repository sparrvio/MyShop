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

    @Column(name = "country", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String country = "RUSSIA";

    @Column(name = "city", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String city = "MOSCOW";

    @Column(name = "street", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String street = "Tverskaya Street";
}

