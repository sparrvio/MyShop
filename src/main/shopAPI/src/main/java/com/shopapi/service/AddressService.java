package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
public interface AddressService {
    void updateAddress(Long id, AddressDTO addressDTO);
}
