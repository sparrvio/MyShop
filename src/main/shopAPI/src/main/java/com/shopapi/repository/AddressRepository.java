package com.shopapi.repository;

import com.shopapi.model.Address;
import com.shopapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long>{}
