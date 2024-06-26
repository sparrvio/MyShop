package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.dto.ProductDTO;
import com.shopapi.dto.SupplierDTO;
import com.shopapi.mapper.ProductMapper;
import com.shopapi.mapper.SupplierMapper;
import com.shopapi.model.Address;
import com.shopapi.model.Supplier;
import com.shopapi.repository.AddressRepository;
import com.shopapi.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Transactional
@Data
@Component
@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;

    public Supplier save(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toSupplier(supplierDTO);
        Address address = new Address();
        supplier.setAddress_id(address);
        addressRepository.save(address);
        return supplierRepository.save(supplier);
    }


    public List<SupplierDTO> findAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SupplierDTO> findById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(supplierMapper.toDTO(supplier.get()));
    }

    @Override
    public void updateProductInSupplier(Long id, ProductDTO productDTO) {
        Supplier supplier = supplierMapper.toSupplier(findById(id).get());
        if (supplier.getProducts() == null) {
            supplier.setProducts(new HashSet<>());
        }
        if (supplier.getProducts().contains(productMapper.toEntity(productDTO))) {
            throw new NoSuchElementException("Product with id  "  + productDTO.getId()  +  " already exists");
        }
            supplier.getProducts().add(productMapper.toEntity(productDTO));
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public void updateAddress(Long id, AddressDTO addressDTO) {
        long addressId = supplierRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier with id " + id + " not found"))
                .getAddress_id()
                .getId();
        addressService.updateAddress(addressId, addressDTO);
    }
}
