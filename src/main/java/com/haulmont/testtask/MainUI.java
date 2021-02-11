package com.haulmont.testtask;

import com.haulmont.testtask.UI.ClientView;
import com.haulmont.testtask.UI.CreditOfferView;
import com.haulmont.testtask.UI.CreditView;
import com.haulmont.testtask.backend.JDBC.JDBCConnect;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.io.IOException;
import java.sql.SQLException;

public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            JDBCConnect.getConnection();
            JDBCConnect.checkDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setMargin(false);
        setContent(layout);
        TabSheet tabSheet = new TabSheet();
        TabSheet bankTabSheet = new TabSheet();
        try {
            bankTabSheet.addTab(new ClientView(), "Клиенты");
            bankTabSheet.addTab(new CreditView(), "Кредиты");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        layout.addComponent(tabSheet);
        tabSheet.addTab(bankTabSheet, "Банк");
        try {
            tabSheet.addTab(new CreditOfferView(), "Предложения по кредиту");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }
}