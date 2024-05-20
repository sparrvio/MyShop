package com.shopapi.service;

import com.shopapi.dto.AddressDTO;
import com.shopapi.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
public interface AddressService {
//    Address createAddress(AddressDTO addressDTO);

    Address createAddress();

    List<Address> getAllAddresses();
}
