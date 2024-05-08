package com.shopapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String clientName;

    @Column(name = "client_surname", nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String clientSurname;

    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    private Date birthday = new Date(0); // Default to 1900-01-01

    @Column(name = "gender", nullable = false, length = 1, columnDefinition = "CHAR(1)")
    private char gender;

    @Column(name = "registration_date", columnDefinition = "DATE")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address_id;

    public Client() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Client(String client_name, Date birthday, Character gender, LocalDate registration_date, Address address_id) {
        this.clientName = clientName;
        this.birthday = birthday;
        this.gender = gender;
        this.registrationDate = registrationDate;
        this.address_id = address_id;
    }
}
