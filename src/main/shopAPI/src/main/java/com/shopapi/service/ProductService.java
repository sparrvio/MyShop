package com.shopapi.service;

import com.shopapi.dto.ProductDTO;
import com.shopapi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDTO>  getById(long id);

    Product save(ProductDTO productDTO);

    List<ProductDTO> getAll();
}
