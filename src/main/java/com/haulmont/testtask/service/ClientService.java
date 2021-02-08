package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.backend.hibernate.Factory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//Класс предназначен для взаимодействия с БД
public class ClientService {
    private Factory factory = Factory.getInstance();

    public void addClient(Client client) throws SQLException {
        factory.getClientDAO().addClient(client);
    }

    public void updateClient(Client client) throws SQLException {
        factory.getClientDAO().updateClient(client);
    }

    public void deleteClient(Client client) throws SQLException {
        factory.getClientDAO().deleteClient(client);
    }

    public List getAllClients() throws SQLException {
        return factory.getClientDAO().getAllClients();
    }

    public Client getClientById(String id) throws SQLException {
        return factory.getClientDAO().getClientById(id);
    }

    public List getClientsByFIO(String fio) throws SQLException {
        if (fio == null){
            return getAllClients();
        } else return factory.getClientDAO().getClientsByFIO(fio);
    }
}
