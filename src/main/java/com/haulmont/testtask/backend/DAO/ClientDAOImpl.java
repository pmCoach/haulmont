package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.JDBC.JDBCConnect;
import com.haulmont.testtask.backend.entities.Client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements  ClientDAO{
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String id = null;
    private String name = null;
    private String secname = null;
    private String patronymic = null;
    private String phone_number = null;
    private String email = null;
    private String pasport_number = null;

    private void setClient(Client client){
        id = client.getId();
        name = client.getName();
        secname = client.getSecname();
        patronymic = client.getPatronymic();
        phone_number = client.getPhoneNumber();
        email = client.getEmail();
        pasport_number = client.getPasportNumber();
    }

    @Override
    public void addClient(Client client) throws SQLException, IOException {
        setClient(client);
        connection = JDBCConnect.getConnection();
        String query = "INSERT INTO client VALUES('" + id + "', '" + name + "', '" + secname + "', '" + patronymic + "', '" + phone_number + "', '" + email + "', '" + pasport_number + "')";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public void updateClient(Client client) throws SQLException, IOException {
        setClient(client);
        connection = JDBCConnect.getConnection();
        String query = "UPDATE client SET name = '" + name + "', secname = '" + secname + "', patronymic = '" + patronymic +"', phone_number = '" + phone_number
                                                +"', email = '" + email + "', pasport_number = '" + pasport_number + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public Client getClientById(String id) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM client WHERE id = '" + id + "'";
        Client client = new Client();
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            client.setId(resultSet.getString("id"));
            client.setName(resultSet.getString("name"));
            client.setSecname(resultSet.getString("secname"));
            client.setPatronymic(resultSet.getString("patronymic"));
            client.setPhoneNumber(resultSet.getString("phone_number"));
            client.setEmail(resultSet.getString("email"));
            client.setPasportNumber(resultSet.getString("pasport_number"));
        }
        connection.close();
        return client;
    }

    @Override
    public List getAllClients() throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Client client = new Client();
            client.setId(resultSet.getString("id"));
            client.setName(resultSet.getString("name"));
            client.setSecname(resultSet.getString("secname"));
            client.setPatronymic(resultSet.getString("patronymic"));
            client.setPhoneNumber(resultSet.getString("phone_number"));
            client.setEmail(resultSet.getString("email"));
            client.setPasportNumber(resultSet.getString("pasport_number"));
            clients.add(client);
        }
        connection.close();
        return clients;
    }

    @Override
    public void deleteClient(Client client) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "DELETE FROM client WHERE id = '" + client.getId() +"'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getClientsByFIO(String fio) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM client WHERE lower(name) like lower(concat('%', '" + fio + "', '%')) or lower(secname) like lower(concat('%', '" + fio + "', '%')) or lower(patronymic) like lower(concat('%', '" + fio + "', '%'))";
        List<Client> clients = new ArrayList<>();
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Client client = new Client();
            client.setId(resultSet.getString("id"));
            client.setName(resultSet.getString("name"));
            client.setSecname(resultSet.getString("secname"));
            client.setPatronymic(resultSet.getString("patronymic"));
            client.setPhoneNumber(resultSet.getString("phone_number"));
            client.setEmail(resultSet.getString("email"));
            client.setPasportNumber(resultSet.getString("pasport_number"));
            clients.add(client);
        }
        connection.close();
        return clients;
    }
}
