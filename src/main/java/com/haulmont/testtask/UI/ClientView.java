package com.haulmont.testtask.UI;

import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.service.ClientService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientView extends VerticalLayout {
    private Grid<Client> clientGrid = new Grid<>(Client.class);
    private TextField filter = new TextField();
    private Label label = new Label("Найти клиента по ФИО");
    private Button add = new Button("Добавить клиента");
    private ClientService clientService = new ClientService();
    private ClientEditUI addUI = new ClientEditUI(new Client(), this);


    public ClientView () throws SQLException, IOException {
        setSizeFull();
        gridConfigure();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponents(clientGrid, addUI);
        gridLayout.setExpandRatio(clientGrid, 1);
        filterConfigure();
        layout.addComponents(label, filter, add);
        add.addClickListener(clickEvent -> {addUI.setVisible(true); addUI.editConfigure(null);});
        addComponents(layout, gridLayout);
    }

    private void filterConfigure(){
        filter.addValueChangeListener(e -> {
            try {
                updateList();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });
        filter.setValueChangeMode(ValueChangeMode.LAZY);
    }

    protected void updateList() throws SQLException, IOException {
        List<Client> clients = clientService.getClientsByFIO(filter.getValue());
        clientGrid.setItems(clients);
    }

    public void updateGrid() throws SQLException, IOException {
        clientGrid.setItems(clientService.getAllClients());
    }


    private void gridConfigure() throws SQLException, IOException {
        clientGrid.setWidth("900");
        clientGrid.removeAllColumns();
        clientGrid.addColumn(Client::getInitials).setCaption("ФИО");
        clientGrid.addColumn(Client::getPhoneNumber).setCaption("Номер телефона");
        clientGrid.addColumn(Client::getEmail).setCaption("Почта");
        clientGrid.addColumn(Client::getPasportNumber).setCaption("Номер паспорта");
        List<Client> clients = clientService.getAllClients();
        clientGrid.setItems(clients);
        clientGrid.asSingleSelect().addSingleSelectionListener(event -> addUI.editConfigure(event.getValue()));
    }
}
