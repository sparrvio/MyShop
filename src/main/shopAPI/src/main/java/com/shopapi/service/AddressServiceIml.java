package com.shopapi.service;

import com.shopapi.model.*;
import com.shopapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceIml implements AddressService{
    @Autowired
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Address getAddressesByClientId(Client client) {
        Address address = addressRepository.getByIdClient(client);
        return address;
    }
}
