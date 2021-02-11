package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.JDBC.JDBCConnect;
import com.haulmont.testtask.backend.entities.Credit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreditDAOImpl implements CreditDAO{
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String id;
    private String credit_name;
    private Long credit_limit;
    private Integer percents;

    private void setCredit(Credit credit){
        id = credit.getId();
        credit_name = credit.getCreditName();
        credit_limit = credit.getCreditLimit();
        percents = credit.getPercent();
    }

    @Override
    public void addCredit(Credit credit) throws SQLException, IOException {
        setCredit(credit);
        connection = JDBCConnect.getConnection();
        String query = "INSERT INTO credit VALUES('" + id + "', '" + credit_name + "', " + credit_limit + " , " + percents + ")";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public void updateCredit(Credit credit) throws SQLException, IOException {
        setCredit(credit);
        connection = JDBCConnect.getConnection();
        String query = "UPDATE credit SET credit_name = '" + credit_name + "', credit_limit = " + credit_limit + ", percent = " + percents +
                " WHERE id = '" + id + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getAllCredits() throws SQLException, IOException {
        List<Credit> credits = new ArrayList<Credit>();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Credit credit = new Credit();
            credit.setId(resultSet.getString("id"));
            credit.setCreditName(resultSet.getString("credit_name"));
            credit.setCreditLimit(resultSet.getLong("credit_limit"));
            credit.setPercent(resultSet.getInt("percent"));
            credits.add(credit);
        }
        connection.close();
        return credits;
    }

    @Override
    public Credit getCreditById(String id) throws SQLException, IOException {
        Credit credit = new Credit();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit WHERE id = '" + id + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            credit.setId(resultSet.getString("id"));
            credit.setCreditName(resultSet.getString("credit_name"));
            credit.setCreditLimit(resultSet.getLong("credit_limit"));
            credit.setPercent(resultSet.getInt("percent"));
        }
        connection.close();
        return credit;
    }

    @Override
    public void deleteCredit(Credit credit) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "DELETE FROM credit WHERE id = '" + credit.getId() + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }


    @Override
    public List getCreditsByName(String name) throws SQLException, IOException {
        List<Credit> credits = new ArrayList<>();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM credit WHERE lower(credit_name) like lower(concat('%', '" + name + "', '%'))";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Credit credit = new Credit();
            credit.setId(resultSet.getString("id"));
            credit.setCreditName(resultSet.getString("credit_name"));
            credit.setCreditLimit(resultSet.getLong("credit_limit"));
            credit.setPercent(resultSet.getInt("percent"));
            credits.add(credit);
        }
        connection.close();
        return credits;
    }
}
