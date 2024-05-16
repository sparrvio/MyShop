package com.shopapi.repository;


import com.shopapi.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Address getByIdClient(Client client) {
        if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Client or its ID cannot be null");
        }

        Session session = entityManager.unwrap(Session.class);
        Query<Address> query = session.createQuery("SELECT a FROM Address a WHERE a.id = :addressId", Address.class);
        query.setParameter("addressId", client.getId()); // Используем ID клиента для поиска адреса
        Address address = query.getSingleResult(); // Получаем результат запроса
        return address;
    }

}
