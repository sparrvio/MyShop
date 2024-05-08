package com.shopapi.repository;

import com.shopapi.model.Client;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ClientRepositoryImpl implements ClientRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {
        Session session = entityManager.unwrap(Session.class);
        Query<Client> query = session.createQuery("from Client", Client.class);
        List<Client> clients = query.getResultList();
        clients.forEach(client -> System.out.println(client.toString()));
        return clients;
    }
}
