package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.Address;
import com.shopapi.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AddressServiceIml implements AddressService {
    private final AddressRepository addressRepository;

//    private final ClientMapper clientMapper;

//    @Override
//    public Address createAddress() {
//        return addressRepository.save(new Address());
//    }
//
//    @Override
//    public List<Address> getAllAddresses() {
//        return StreamSupport.stream(addressRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
//    }

    @Override
    public void updateAddress(Long id, AddressDTO addressDTO) {
        try {
            Address address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
            address.setCountry(addressDTO.getCountry());
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
