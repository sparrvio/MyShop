package com.shopapi.service;

import com.shopapi.mapper.ClientMapper;
import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceIml implements AddressService{
    @Autowired
    private final AddressRepository addressRepository;

//    @Autowired
    private final ClientMapper clientMapper;
    @Override
    public Address createAddress() {
        return addressRepository.save(new Address());
    }


    @Override
    public List<Address> getAllAddresses() {
        return StreamSupport.stream(addressRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
