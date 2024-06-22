package com.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
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

    @Column(name = "client_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String clientName;

    @Column(name = "client_surname", columnDefinition = "VARCHAR(255)")
    private String clientSurname;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday; // Default to 1900-01-01

    @Column(name = "gender", length = 1, columnDefinition = "CHAR(1)")
    @Check(constraints = "gender in ('M', 'F', 'm', 'f')")
    private char gender;

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE")
    @ColumnDefault(value = "CURRENT_DATE")
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address_id;
}

