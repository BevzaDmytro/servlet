package com.kpi.model;

import com.kpi.entities.Account;

public class AccountsDao {

    public void update(Account account){
        Connector connector = Connector.getInstance();
        String query = "UPDATE accounts SET balance = "+account.getBalance()+"WHERE id = "+account.getId();
        connector.executeSQL(query);

    }
}
