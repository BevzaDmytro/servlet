package com.kpi.entities;

public class Card {
    private int id;
    private String cardNum;
    private int cardOwner;
    private Account account;
    private boolean blocked;

    public Card(int id, String cardNum, int cardOwner, boolean blocked, Account account) {
        this.id = id;
        this.cardNum = cardNum;
        this.cardOwner = cardOwner;
        this.account = account;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(int cardOwner) {
        this.cardOwner = cardOwner;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Card() {

    }
}
