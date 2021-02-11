package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.entities.Credit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CreditDAO {
    public void addCredit(Credit credit) throws SQLException, IOException;
    public void updateCredit(Credit credit) throws SQLException, IOException;
    public List getAllCredits() throws SQLException, IOException;
    public Credit getCreditById(String id) throws SQLException, IOException;
    public void deleteCredit(Credit credit) throws SQLException, IOException;
    public List getCreditsByName(String name) throws SQLException, IOException;
}
