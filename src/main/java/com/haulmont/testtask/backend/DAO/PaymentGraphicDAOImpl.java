package com.haulmont.testtask.backend.DAO;

import com.haulmont.testtask.backend.JDBC.JDBCConnect;
import com.haulmont.testtask.backend.entities.CreditOffer;
import com.haulmont.testtask.backend.entities.PaymentGraphic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentGraphicDAOImpl implements PaymentGraphicDAO{
    Connection connection = null;
    ResultSet resultSet = null;
    private String id;
    private Long credit_body;
    private Long credit_percents;
    private Date date;
    private Long ostatok;
    private Long payment_sum;
    private String credit_offer_id;
    private CreditOfferDAOImpl creditOfferDAO = new CreditOfferDAOImpl();

    private void setPaymentGraphic(PaymentGraphic paymentGraphic){
        id = paymentGraphic.getId();
        credit_body = paymentGraphic.getCreditBody();
        credit_percents = paymentGraphic.getCreditPercents();
        date = new Date(paymentGraphic.getOrigDate().getTime());
        ostatok = paymentGraphic.getOstatok();
        payment_sum = paymentGraphic.getPaymentSum();
        credit_offer_id = paymentGraphic.getCreditOffer().getId();
    }

    @Override
    public void addPaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        setPaymentGraphic(paymentGraphic);
        connection = JDBCConnect.getConnection();
        String query = "INSERT INTO payment_graphic VALUES('" + id + "', " + credit_body + ", " + credit_percents + ", '" + date.toString() + "', " + ostatok + ", " + payment_sum + " , '" + credit_offer_id + "')";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public void updatePaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        setPaymentGraphic(paymentGraphic);
        connection = JDBCConnect.getConnection();
        String query = "UPDATE payment_graphic SET credit_body = " + credit_body + ", credit_percents = " + credit_percents + ", date = '" + date.toString() + "', ostatok = " + ostatok + ", payment_sum = " + payment_sum + " , credit_offer_id = '" + credit_offer_id + "')";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getAllPaymentGraphic() throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        List<PaymentGraphic> paymentGraphics = new ArrayList<>();
        String query = "SELECT * FROM payment_graphic";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            PaymentGraphic paymentGraphic = new PaymentGraphic();
            paymentGraphic.setId(resultSet.getString("id"));
            paymentGraphic.setCreditBody(resultSet.getLong("credit_body"));
            paymentGraphic.setCreditPercents(resultSet.getLong("credit_percents"));
            paymentGraphic.setDate(resultSet.getDate("date"));
            paymentGraphic.setOstatok(resultSet.getLong("ostatok"));
            paymentGraphic.setPaymentSum(resultSet.getLong("payment_sum"));
            paymentGraphic.setCreditOffer(creditOfferDAO.getCreditOfferById(resultSet.getString("credit_offer_id")));
            paymentGraphics.add(paymentGraphic);
        }
        connection.close();
        return paymentGraphics;
    }


    @Override
    public PaymentGraphic getPaymentGraphicById(String id) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        PaymentGraphic paymentGraphic = new PaymentGraphic();
        String query = "SELECT * FROM payment_graphic WHERE id = '" + id + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            paymentGraphic.setId(resultSet.getString("id"));
            paymentGraphic.setCreditBody(resultSet.getLong("credit_body"));
            paymentGraphic.setCreditPercents(resultSet.getLong("credit_percents"));
            paymentGraphic.setDate(resultSet.getDate("date"));
            paymentGraphic.setOstatok(resultSet.getLong("ostatok"));
            paymentGraphic.setPaymentSum(resultSet.getLong("payment_sum"));
            paymentGraphic.setCreditOffer(creditOfferDAO.getCreditOfferById(resultSet.getString("credit_offer_id")));
        }
        connection.close();
        return paymentGraphic;
    }

    @Override
    public void deletePaymentGraphic(PaymentGraphic paymentGraphic) throws SQLException, IOException {
        connection = JDBCConnect.getConnection();
        String query = "DELETE FROM payment_graphic WHERE id = '" + paymentGraphic.getId() + "'";
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Override
    public List getPaymentGraphicsByCreditOffer(CreditOffer creditOffer) throws SQLException, IOException {
        List<PaymentGraphic> paymentGraphics = new ArrayList<>();
        connection = JDBCConnect.getConnection();
        String query = "SELECT * FROM payment_graphic WHERE credit_offer_id = '" + creditOffer.getId() + "'";
        resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            PaymentGraphic paymentGraphic = new PaymentGraphic();
            paymentGraphic.setId(resultSet.getString("id"));
            paymentGraphic.setCreditBody(resultSet.getLong("credit_body"));
            paymentGraphic.setCreditPercents(resultSet.getLong("credit_percents"));
            paymentGraphic.setDate(resultSet.getDate("date"));
            paymentGraphic.setOstatok(resultSet.getLong("ostatok"));
            paymentGraphic.setPaymentSum(resultSet.getLong("payment_sum"));
            paymentGraphic.setCreditOffer(creditOfferDAO.getCreditOfferById(resultSet.getString("credit_offer_id")));
            paymentGraphics.add(paymentGraphic);
        }
        connection.close();
        return paymentGraphics;
    }
}
