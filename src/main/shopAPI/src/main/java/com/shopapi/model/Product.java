package com.shopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.*;


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

    @PrePersist
    protected void setLastUpdateDateToYesterday() {
        this.last_update_date = getPreviousDay(LocalDate.now());
    }
    @PreUpdate
    protected void setLastUpdateDateToCurrent() {
        this.last_update_date = LocalDate.now();
    }
    private LocalDate getPreviousDay(LocalDate date) {
        return date.minusDays(1);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_supplier",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(
            mappedBy = "product_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Images> images = new HashSet<>();
}
