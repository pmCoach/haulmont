package com.haulmont.testtask.UI;

import com.haulmont.testtask.backend.entities.Client;
import com.haulmont.testtask.service.ClientService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import org.hibernate.event.spi.EventType;


import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

//Класс предназначен для формирования страницы для работой со списком клиентов
public class ClientView extends VerticalLayout {
    private Grid<Client> clientGrid = new Grid<>(Client.class);
    private TextField filter = new TextField();
    private Button add = new Button("Add");
    private ClientService clientService = new ClientService();
    private ClientEditUI addUI = new ClientEditUI(new Client(), this);


    public ClientView () throws SQLException {
        setSizeFull();
        gridConfigure();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponents(clientGrid, addUI);
        gridLayout.setExpandRatio(clientGrid, 1);
        filterConfigure();
        layout.addComponents(filter, add);
        add.addClickListener(clickEvent -> {addUI.setVisible(true); addUI.editConfigure(null);});
        addComponents(layout, gridLayout);
    }

    //Метод конфигурирует поле поиска по ФИО
    private void filterConfigure(){
        filter.addValueChangeListener(e -> {
            try {
                updateList();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        filter.setValueChangeMode(ValueChangeMode.LAZY);
    }

    //Метод обновляет список клиентов
    protected void updateList() throws SQLException{
        List<Client> clients = clientService.getClientsByFIO(filter.getValue());
        clientGrid.setItems(clients);
    }

    //Метод также обновляет список клиентов
    public void updateGrid() throws SQLException{
        clientGrid.setItems(clientService.getAllClients());
    }

    //Метод предназначен для конфигурирования списка клиентов
    private void gridConfigure() throws SQLException {
        clientGrid.setWidth("900");
        clientGrid.removeColumn("id");
        clientGrid.removeColumn("creditOffers");
        clientGrid.setColumns("secname", "name", "patronymic", "phoneNumber", "email", "pasportNumber");
        List<Client> clients = clientService.getAllClients();
        clientGrid.setItems(clients);
        clientGrid.asSingleSelect().addSingleSelectionListener(event -> addUI.editConfigure(event.getValue()));
    }
}
