package com.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "category", columnDefinition = "VARCHAR(255)")
    private String category;
    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private Double price;
    @Column(name = "available_stock", nullable = false, columnDefinition = "INTEGER")
    private Integer available_stock;
    @Column(name = "last_update_date", columnDefinition = "DATE")
    @ColumnDefault(value = "CURRENT_DATE")
    private LocalDate last_update_date;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier_id;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Images> images = new HashSet<>();
}
