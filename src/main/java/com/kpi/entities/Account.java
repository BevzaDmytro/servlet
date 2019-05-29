package com.kpi.entities;

public class Account {
    private int id;
    private int accountNum;
    private  float balance;

    public Account(int id, int accountNum, float balance) {
        this.id = id;
        this.accountNum = accountNum;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
