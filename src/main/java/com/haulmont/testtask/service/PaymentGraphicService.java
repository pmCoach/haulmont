package com.haulmont.testtask.service;

import com.haulmont.testtask.backend.DAO.PaymentGraphicDAO;
import com.haulmont.testtask.backend.entities.CreditOffer;
import com.haulmont.testtask.backend.entities.PaymentGraphic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentGraphicService {
    PaymentGraphicDAO factory = Factory.getInstance().getPaymentGraphicDAO();

    public void addPaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        factory.addPaymentGraphic(paymentGraphic);
    }
    public void updatePaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        factory.updatePaymentGraphic(paymentGraphic);
    }
    public void deletePaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        factory.deletePaymentGraphic(paymentGraphic);
    }
    public List getAllPaymentGraphics() throws SQLException, IOException {
        return factory.getAllPaymentGraphic();
    }
    public PaymentGraphic getPaymentGraphicById(String id) throws SQLException, IOException {
        return factory.getPaymentGraphicById(id);
    }
    public List getPaymentGraphicByCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        if (creditOffer == null){
            return getAllPaymentGraphics();
        } else {
            return factory.getPaymentGraphicsByCreditOffer(creditOffer);
        }
    }
}
