package com.shopapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "images")
public class Images {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "image", nullable = false)
    private byte[] bytes;
}
