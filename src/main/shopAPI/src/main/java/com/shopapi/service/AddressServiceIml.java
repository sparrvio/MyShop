package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.Address;
import com.shopapi.model.Client;
import com.shopapi.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class AddressServiceIml implements AddressService{
//    @Autowired
//    private final AddressRepository addressRepository;
//
//    @Override
//    @Transactional
//    public Address getAddressesByClientId(Client client) {
//        Address address = addressRepository.getByIdClient(client);
//        return address;
//    }
//}
