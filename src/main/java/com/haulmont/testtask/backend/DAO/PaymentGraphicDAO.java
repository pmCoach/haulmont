package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.entities.CreditOffer;
import com.haulmont.testtask.backend.entities.PaymentGraphic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PaymentGraphicDAO {
    public void addPaymentGraphic (PaymentGraphic paymentGraphic) throws SQLException, IOException;
    public void updatePaymentGraphic (PaymentGraphic paymentGraphic) throws SQLException, IOException;
    public List getAllPaymentGraphic () throws SQLException, IOException;
    public PaymentGraphic getPaymentGraphicById(String id) throws SQLException, IOException;
    public void deletePaymentGraphic (PaymentGraphic paymentGraphic) throws SQLException, IOException;
    public List getPaymentGraphicsByCreditOffer(CreditOffer creditOffer) throws SQLException, IOException;
}
