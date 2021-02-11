package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.DAO.CreditOfferDAO;
import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.backend.entities.Credit;
import com.haulmont.testtask.backend.entities.CreditOffer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreditOfferService {
    private CreditOfferDAO factory = Factory.getInstance().getCreditOfferDAO();

    public void addCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        factory.addCreditOffer(creditOffer);
        System.out.println("Credit sum" + creditOffer.getCreditSum());
        System.out.println("Credit_id" + creditOffer.getCredit().getId());
        System.out.println("Client_id" + creditOffer.getClient().getId());
    }
    public void updateCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        factory.updateCreditOffer(creditOffer);
    }
    public void deleteCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        factory.deleteCreditOffer(creditOffer);
    }
    public List getAllCreditOffers() throws SQLException, IOException {
        return factory.getAllCreditOffers();
    }
    public CreditOffer getCreditOfferById(String id) throws SQLException, IOException {
        return factory.getCreditOfferById(id);
    }
    public List getCreditOfferByClient(Client client) throws SQLException, IOException {
        return factory.getCreditOffersByClient(client);
    }
    public List getCreditOfferByCredit(Credit credit) throws SQLException, IOException {
        return factory.getCreditOffersByCredit(credit);
    }
}
