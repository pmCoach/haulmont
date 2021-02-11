package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.DAO.CreditDAO;
import com.haulmont.testtask.backend.entities.Credit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreditService {
    CreditDAO factory = Factory.getInstance().getCreditDAO();



    public void addCredit(Credit credit) throws SQLException, IOException {
        factory.addCredit(credit);
    }
    public void updateCredit(Credit credit) throws SQLException, IOException {
        factory.updateCredit(credit);
    }
    public void deleteCredit(Credit credit) throws SQLException, IOException {
        factory.deleteCredit(credit);
    }

    public List getAllCredits() throws SQLException, IOException {
        return factory.getAllCredits();
    }

    public Credit getCreditById(String id) throws SQLException, IOException {
        return factory.getCreditById(id);
    }

    public List getCreditByName(String name) throws SQLException, IOException {
        if (name == null){
            return getAllCredits();
        } else
        return factory.getCreditsByName(name);
    }
}
