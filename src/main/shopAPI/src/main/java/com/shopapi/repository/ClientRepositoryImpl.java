package com.shopapi.repository;

import com.shopapi.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {
        Session session = entityManager.unwrap(Session.class);
        Query<Client> query = session.createQuery("from Client", Client.class);
        List<Client> clients = query.getResultList();
        return clients;
    }
}
