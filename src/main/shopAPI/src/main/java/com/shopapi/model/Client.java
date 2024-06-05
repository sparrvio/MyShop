package com.shopapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Column(name = "client_name", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String clientName;

    @Column(name = "client_surname", length = 255, columnDefinition = "VARCHAR(255)")
    private String clientSurname;

    @Column(name = "birthday", columnDefinition = "DATE")
    private LocalDate birthday; // Default to 1900-01-01

    @Column(name = "gender", length = 1, columnDefinition = "CHAR(1)")
    @Check(constraints = "gender in ('M', 'F')")
    private char gender;

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE")
    @ColumnDefault(value = "CURRENT_DATE")
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address_id;
}

