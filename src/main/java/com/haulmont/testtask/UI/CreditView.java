package com.haulmont.testtask.UI;

import com.haulmont.testtask.backend.entities.Credit;
import com.haulmont.testtask.service.CreditService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

import java.sql.SQLException;
import java.util.List;

//Класс предназначен для формирования страницы для работы со списком Кредитов
public class CreditView extends VerticalLayout {
    private Grid<Credit> creditGrid = new Grid<>(Credit.class);
    private TextField filter = new TextField();
    private Button add = new Button("Add");
    private CreditService creditService = new CreditService();
    private CreditEditUI addUI = new CreditEditUI(new Credit(), this);


    public CreditView () throws SQLException {
        setSizeFull();
        gridConfigure();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponents(creditGrid, addUI);
        gridLayout.setExpandRatio(creditGrid, 1);
        filterConfigure();
        layout.addComponents(filter, add);
        add.addClickListener(clickEvent -> {addUI.setVisible(true); addUI.editConfigure(null);});
        addComponents(layout, gridLayout);
    }

    //Метод для поиска кредита по названию
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

    //Метод для обновления списка кредитов
    protected void updateList() throws SQLException{
        List<Credit> credits = creditService.getCreditByName(filter.getValue());
        creditGrid.setItems(credits);
    }

    //Так же метод для обновления списка кредитов
    public void updateGrid() throws SQLException{
        creditGrid.setItems(creditService.getAllCredits());
    }

    //Метод для конфигурации списка кредитов
    private void gridConfigure() throws SQLException {
        creditGrid.setWidth("900");
        creditGrid.removeColumn("id");
        creditGrid.removeColumn("creditOffers");
        creditGrid.setColumns("creditName", "percent", "creditLimit");
        List<Credit> credits = creditService.getAllCredits();
        creditGrid.setItems(credits);
        creditGrid.asSingleSelect().addSingleSelectionListener(event -> addUI.editConfigure(event.getValue()));
    }
}
