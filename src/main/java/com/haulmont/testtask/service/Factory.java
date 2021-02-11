package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.DAO.ClientDAOImpl;
import com.haulmont.testtask.backend.DAO.CreditDAOImpl;
import com.haulmont.testtask.backend.DAO.CreditOfferDAOImpl;
import com.haulmont.testtask.backend.DAO.PaymentGraphicDAOImpl;

public class Factory {

    public static Factory getInstance(){
            return new Factory();
    }
    public ClientDAOImpl getClientDAO(){
        return new ClientDAOImpl();
    }

    public CreditDAOImpl getCreditDAO(){
            return new CreditDAOImpl();
    }

    public CreditOfferDAOImpl getCreditOfferDAO(){
            return new CreditOfferDAOImpl();
    }

    public PaymentGraphicDAOImpl getPaymentGraphicDAO(){
            return new PaymentGraphicDAOImpl();
    }
}
