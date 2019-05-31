package com.kpi.services;

import com.kpi.entities.Card;
import com.kpi.model.CardsDao;

public class CardsService {

    public void block(String cardNum){
        CardsDao dao = new CardsDao();
        Card card = dao.getCardByNumber(cardNum);
        card.setBlocked(true);
        dao.updateCard(card);
    }

    public void unblock(String cardNum) {
        CardsDao dao = new CardsDao();
        Card card = dao.getCardByNumber(cardNum);
        card.setBlocked(false);
        dao.updateCard(card);
    }
}
