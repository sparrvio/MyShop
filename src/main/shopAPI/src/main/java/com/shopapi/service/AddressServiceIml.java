package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util. *;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceIml implements AddressService{
    @Autowired
    private final AddressRepository addressRepository;
    @Override

    public Address createAddress(AddressDTO addressDTO) {
        Address address = Address.builder()
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .street(addressDTO.getStreet())
                .build();
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddresses() {
        return StreamSupport.stream(addressRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
