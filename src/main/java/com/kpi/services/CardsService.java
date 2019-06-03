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

    public void block(Card cardToBlock) {
        CardsDao dao = new CardsDao();
        cardToBlock.setBlocked(true);
        dao.updateCard(cardToBlock);
    }

    public Iterable<Card> getCardsByOwnerId(int id){
        CardsDao cardsDao = new CardsDao();
        return cardsDao.getCardsByOwnerId(id);
    }
}
