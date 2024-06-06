package com.shopapi.repository;

import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
