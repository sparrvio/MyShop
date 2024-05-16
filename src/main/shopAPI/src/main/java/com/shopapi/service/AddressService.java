package com.shopapi.service;

import com.shopapi.model.*;

public interface AddressService {
    public Address getAddressesByClientId(Client client);
}
