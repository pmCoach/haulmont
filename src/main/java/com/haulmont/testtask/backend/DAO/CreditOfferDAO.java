package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.backend.entities.Credit;
import com.haulmont.testtask.backend.entities.CreditOffer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CreditOfferDAO {
    public void addCreditOffer(CreditOffer creditOffer) throws SQLException, IOException;
    public void updateCreditOffer(CreditOffer creditOffer) throws SQLException, IOException;
    public List getAllCreditOffers() throws SQLException, IOException;
    public CreditOffer getCreditOfferById(String id) throws SQLException, IOException;
    public void deleteCreditOffer(CreditOffer creditOffer) throws SQLException, IOException;
    public List getCreditOffersByClient(Client client) throws SQLException, IOException;
    public List getCreditOffersByCredit(Credit credit) throws SQLException, IOException;
}
