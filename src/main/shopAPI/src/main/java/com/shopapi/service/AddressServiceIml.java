package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.mapper.AddressMapper;
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
    private final AddressMapper addressMapper;

    @Override
    public void updateAddress(Long id, AddressDTO addressDTO) {
        try {
            Address address = addressMapper.convertToEntity(addressDTO);
            address.setId(id);
            addressRepository.save(address);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAddressForHtml(long id, String country, String city, String street)  {
        Address address = Address.builder()
                .country(country)
                .city(city)
                .street(street)
                .build();
        addressRepository.save(address);
    }
}
