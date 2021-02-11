package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.entities.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ClientService {
    private Factory factory = Factory.getInstance();

    public void addClient(Client client) throws SQLException, IOException {
        factory.getClientDAO().addClient(client);
    }

    public void updateClient(Client client) throws SQLException, IOException {
        factory.getClientDAO().updateClient(client);
    }

    public void deleteClient(Client client) throws SQLException, IOException {
        factory.getClientDAO().deleteClient(client);
    }

    public List getAllClients() throws SQLException, IOException {
        return factory.getClientDAO().getAllClients();
    }

    public Client getClientById(String id) throws SQLException, IOException {
        return factory.getClientDAO().getClientById(id);
    }

    public List getClientsByFIO(String fio) throws SQLException, IOException {
        if (fio == null){
            return getAllClients();
        } else return factory.getClientDAO().getClientsByFIO(fio);
    }
}
