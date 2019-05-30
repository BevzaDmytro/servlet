package com.kpi.services;

import com.kpi.entities.Card;
import com.kpi.model.AccountsDao;
import com.kpi.model.CardsDao;

public class PaymentsService {

    private String cardNum1;
    private String cardNum2;

    public PaymentsService(String cardNum1, String cardNum2) {
        this.cardNum1 = cardNum1;
        this.cardNum2 = cardNum2;
    }

    public String pay(String amount){

        float money = Float.valueOf(amount);


        CardsDao cardsDao = new CardsDao();
        Card card1 = cardsDao.getCardByNumber(this.cardNum1);

        Card card2 = cardsDao.getCardByNumber(this.cardNum2);
        if(card2 == null) return "recipient card does not exist";
        if(card1.isBlocked() || card2.isBlocked()) return "Card is blocked";
        if(card1.getAccount().getBalance() < money) return "Not enough money";

        card1.getAccount().setBalance(card1.getAccount().getBalance() - money);
        card2.getAccount().setBalance(card2.getAccount().getBalance() + money);
        AccountsDao accountsDao = new AccountsDao();
        accountsDao.update(card1.getAccount());
        accountsDao.update(card2.getAccount());

        return "Payment successful";
    }
}
