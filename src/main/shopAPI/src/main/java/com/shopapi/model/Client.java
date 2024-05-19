package com.shopapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String clientName;

    @Column(name = "client_surname", length = 255, columnDefinition = "VARCHAR(255)")
    private String clientSurname;

    @Column(name = "birthday", columnDefinition = "DATE")
    private Date birthday = new Date(0); // Default to 1900-01-01

    @Column(name = "gender", length = 1, columnDefinition = "CHAR(1)")
    private char gender;

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address_id;
}

