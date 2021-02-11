package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.JDBC.JDBCConnect;
import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.backend.entities.Credit;
import com.haulmont.testtask.backend.entities.CreditOffer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditOfferDAOImpl implements CreditOfferDAO{
    CreditDAOImpl creditDAO = new CreditDAOImpl();
    ClientDAOImpl clientDAO = new ClientDAOImpl();
    private Connection connection = null;
    private ResultSet resultSet = null;
    private String id;
    private Long credit_sum;
    private Long months_of_credit;
    private String client_id;
    private String credit_id;

    private void setCreditOffer(CreditOffer creditOffer){
      id = creditOffer.getId();
      credit_sum = creditOffer.getCreditSum();
      months_of_credit = creditOffer.getMonthsOfCredit();
      client_id = creditOffer.getClient().getId();
      credit_id = creditOffer.getCredit().getId();
    }
    @Override
    public void addCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        setCreditOffer(creditOffer);
        connection = JDBCConnect.getConnection();
        String query = "INSERT INTO credit_offer VALUES('" + id + "', " + credit_sum + ", " + months_of_credit + ", '" + client_id + "', '" + credit_id + "')";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public void updateCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        setCreditOffer(creditOffer);
        connection = JDBCConnect.getConnection();
        String query = "UPDATE credit_offer SET credit_sum = " + credit_sum + ", months_of_credit = " + months_of_credit + ", client_id = '" + client_id + "', credit_id = '" + credit_id + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getAllCreditOffers() throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        List<CreditOffer> creditOffers = new ArrayList<>();
        String query = "SELECT * FROM credit_offer";
        resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()){
            CreditOffer creditOffer = new CreditOffer();
            creditOffer.setId(resultSet.getString("id"));
            creditOffer.setCreditSum(resultSet.getLong("credit_sum"));
            creditOffer.setMonthsOfCredit(resultSet.getLong("months_of_credit"));
            creditOffer.setCredit(creditDAO.getCreditById(resultSet.getString("credit_id")));
            creditOffer.setClient(clientDAO.getClientById(resultSet.getString("client_id")));
            creditOffers.add(creditOffer);
        }
        connection.close();
        return creditOffers;
    }

    @Override
    public CreditOffer getCreditOfferById(String id) throws SQLException, IOException {
        CreditOffer creditOffer = new CreditOffer();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit_offer WHERE id = '" + id + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            creditOffer.setId(resultSet.getString("id"));
            creditOffer.setCreditSum(resultSet.getLong("credit_sum"));
            creditOffer.setMonthsOfCredit(resultSet.getLong("months_of_credit"));
            creditOffer.setCredit(creditDAO.getCreditById(resultSet.getString("credit_id")));
            creditOffer.setClient(clientDAO.getClientById(resultSet.getString("client_id")));
        }
        connection.close();
        return creditOffer;
    }

    @Override
    public void deleteCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "DELETE FROM credit_offer WHERE id = '" + creditOffer.getId() + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getCreditOffersByClient(Client client) throws SQLException, IOException {
        List creditOffers = new ArrayList<CreditOffer>();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit_offer WHERE client_id = '" + client.getId() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            CreditOffer creditOffer = new CreditOffer();
            creditOffer.setId(resultSet.getString("id"));
            creditOffer.setCreditSum(resultSet.getLong("credit_sum"));
            creditOffer.setMonthsOfCredit(resultSet.getLong("months_of_credit"));
            creditOffer.setCredit(creditDAO.getCreditById(resultSet.getString("credit_id")));
            creditOffer.setClient(clientDAO.getClientById(resultSet.getString("client_id")));
            creditOffers.add(creditOffer);
        }
        connection.close();
        return creditOffers;
    }

    @Override
    public List getCreditOffersByCredit(Credit credit) throws SQLException, IOException {
        List creditOffers = new ArrayList<CreditOffer>();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit_offer WHERE credit_id = '" + credit.getId() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            CreditOffer creditOffer = new CreditOffer();
            creditOffer.setId(resultSet.getString("id"));
            creditOffer.setCreditSum(resultSet.getLong("credit_sum"));
            creditOffer.setMonthsOfCredit(resultSet.getLong("months_of_credit"));
            creditOffer.setCredit(creditDAO.getCreditById(resultSet.getString("credit_id")));
            creditOffer.setClient(clientDAO.getClientById(resultSet.getString("client_id")));
            creditOffers.add(creditOffer);
        }
        connection.close();
        return creditOffers;
    }
}
