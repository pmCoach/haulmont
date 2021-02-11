package com.haulmont.testtask.UI;

import com.haulmont.testtask.backend.entities.CreditOffer;
import com.haulmont.testtask.backend.entities.PaymentGraphic;
import com.haulmont.testtask.service.CreditOfferService;
import com.vaadin.ui.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreditOfferView extends VerticalLayout {
    private Grid<CreditOffer> creditGrid = new Grid<>(CreditOffer.class);
    public Grid<PaymentGraphic> paymentGrid = new Grid<>(PaymentGraphic.class);
    private Button add = new Button("Добавить кредитное предложение");
    private CreditOfferService creditOfferService = new CreditOfferService();
    private CreditOfferEditUI addUI = new CreditOfferEditUI(new CreditOffer(), this);


    public CreditOfferView () throws SQLException, IOException {
        setSizeFull();
        gridConfigure();
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        gridLayout.setSizeFull();
        gridLayout.addComponents(creditGrid, addUI);
        paymentGrid.setVisible(false);
        layout.addComponents(add);
        add.addClickListener(clickEvent -> {
            addUI.setVisible(true);
            addUI.editConfigure(null);
        });
        addComponents(layout, gridLayout, paymentGrid);
    }



    protected void updateList() throws SQLException, IOException {
        List<CreditOffer> creditOffers = creditOfferService.getCreditOfferByCredit(null);
        creditGrid.setItems(creditOffers);
    }

    public void updateGrid() throws SQLException, IOException {
        creditGrid.setItems(creditOfferService.getAllCreditOffers());
    }

    public void paymentGridConfigure(List<PaymentGraphic> paymentGraphics) throws SQLException{
        paymentGrid.setWidth("800");
        paymentGrid.setVisible(true);
        paymentGrid.setItems(paymentGraphics);
        paymentGrid.removeAllColumns();
        paymentGrid.addColumn(PaymentGraphic::getPaymentSum).setCaption("Сумма платежа");
        paymentGrid.addColumn(PaymentGraphic::getCreditBody).setCaption("Тело кредита");
        paymentGrid.addColumn(PaymentGraphic::getCreditPercents).setCaption("Процентная оплата");
        paymentGrid.addColumn(PaymentGraphic::getOstatok).setCaption("Остаток");
        paymentGrid.addColumn(column -> column.getOrigDate().toString()).setCaption("Дата");
    }


    private void gridConfigure() throws SQLException, IOException {
        creditGrid.setWidth("800");
        creditGrid.removeAllColumns();
        creditGrid.addColumn(CreditOffer::getCreditSum).setCaption("Сумма кредита");
        creditGrid.addColumn(CreditOffer::getMonthsOfCredit).setCaption("Срок кредита");
        creditGrid.addColumn(client -> client.getClient().getInitials()).setCaption("Клиент");
        creditGrid.addColumn(credit -> credit.getCredit().getCreditName()).setCaption("Кредит");
        List<CreditOffer> credits = creditOfferService.getAllCreditOffers();
        creditGrid.setItems(credits);
        creditGrid.asSingleSelect().addSingleSelectionListener(event -> addUI.editConfigure(event.getValue()));
    }
}
