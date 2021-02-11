package com.haulmont.testtask.backend.JDBC;

import com.haulmont.testtask.service.Factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {

    public static Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:database", "SA", "");
        return connection;
    }

    public static void checkDatabase() throws IOException, SQLException {
        ScriptRunner scriptRunner = new ScriptRunner(getConnection(), true, true);
        scriptRunner.runScript(new BufferedReader(new FileReader("src/main/java/com/haulmont/testtask/sql/tables.sql")));
        if (Factory.getInstance().getClientDAO().getAllClients().isEmpty() && Factory.getInstance().getCreditDAO().getAllCredits().isEmpty() && Factory.getInstance().getCreditOfferDAO().getAllCreditOffers().isEmpty())
        {
            scriptRunner.runScript(new BufferedReader(new FileReader("src/main/java/com/haulmont/testtask/sql/client.sql")));
            scriptRunner.runScript(new BufferedReader(new FileReader("src/main/java/com/haulmont/testtask/sql/credit.sql")));
            scriptRunner.runScript(new BufferedReader(new FileReader("src/main/java/com/haulmont/testtask/sql/credit_offer.sql")));
        }

    }

}
