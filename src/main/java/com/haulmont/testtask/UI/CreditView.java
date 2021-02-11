package com.haulmont.testtask.UI;

import com.haulmont.testtask.backend.entities.Credit;
import com.haulmont.testtask.service.CreditService;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreditView extends VerticalLayout {
    private Grid<Credit> creditGrid = new Grid<>(Credit.class);
    private TextField filter = new TextField();
    private Label label = new Label("Найти кредит по названию");
    private Button add = new Button("Добавить кредит");
    private CreditService creditService = new CreditService();
    private CreditEditUI addUI = new CreditEditUI(new Credit(), this);


    public CreditView () throws SQLException, IOException {
        setSizeFull();
        gridConfigure();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponents(creditGrid, addUI);
        gridLayout.setExpandRatio(creditGrid, 1);
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
        System.out.println(filter.getValue());
        List<Credit> credits = creditService.getCreditByName(filter.getValue());
        creditGrid.setItems(credits);
    }

    public void updateGrid() throws SQLException, IOException {
        creditGrid.setItems(creditService.getAllCredits());
    }


    private void gridConfigure() throws SQLException, IOException {
        creditGrid.setWidth("900");
        creditGrid.removeAllColumns();
        creditGrid.addColumn(Credit::getCreditName).setCaption("Название кредита");
        creditGrid.addColumn(Credit::getPercent).setCaption("Процент");
        creditGrid.addColumn(Credit::getCreditLimit).setCaption("Кредитный лимит");
        List<Credit> credits = creditService.getAllCredits();
        creditGrid.setItems(credits);
        creditGrid.asSingleSelect().addSingleSelectionListener(event -> addUI.editConfigure(event.getValue()));
    }
}
