package com.shopapi.repository;

import com.shopapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository {
    public List<Client> getAllClients();
}
