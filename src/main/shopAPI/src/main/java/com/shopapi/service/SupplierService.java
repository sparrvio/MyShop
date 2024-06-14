package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.dto.SupplierDTO;
import com.shopapi.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierDTO> findAll();
    Optional<SupplierDTO> findById(Long id);
    void deleteById(Long id);
    void updateAddress(Long id, AddressDTO addressDTO);
    Supplier save(SupplierDTO supplierDTO);
    void updateProductInSupplier(Long id, ProductDTO productDTO);
}
