package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.entities.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    public void addClient(Client client) throws SQLException, IOException;
    public void updateClient(Client client) throws SQLException, IOException;
    public Client getClientById(String id) throws SQLException, IOException;
    public List getAllClients() throws SQLException, IOException;
    public void deleteClient(Client client) throws SQLException, IOException;
    public List getClientsByFIO(String client) throws SQLException, IOException;
}
