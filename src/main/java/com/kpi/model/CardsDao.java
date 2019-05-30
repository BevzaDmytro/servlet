package com.kpi.model;

import com.kpi.entities.Account;
import com.kpi.entities.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardsDao {

    public void setCardBlocked(String cardNumber){
        Connector connector = Connector.getInstance();
        String query = "UPDATE cards SET is_blocked = 'true' WHERE card_number = "+cardNumber;

        ResultSet result = connector.executeSQLWithReturn(query);
        try {
            result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCardUnBlocked(String cardNumber){
        Connector connector = Connector.getInstance();
        String query = "UPDATE cards SET is_blocked = 'false' WHERE card_number = "+cardNumber;

         connector.executeSQL(query);

    }

    public void updateCard(Card card){
        Connector connector = Connector.getInstance();
        String query = "UPDATE cards SET is_blocked = '"+String.valueOf(card.isBlocked())+"' WHERE id="+card.getId();
        connector.executeSQL(query);
    }

    public Card getCardByNumber(String cardNum1) {
        Card card = null;
        Connector connector = Connector.getInstance();
        String query = "SELECT * FROM cards JOIN accounts ON cards.account_id = accounts.id WHERE card_number = '"+cardNum1+"'";

        ResultSet result = connector.executeSQLWithReturn(query);

        try {
            result.next();

            card = new Card(result.getInt("id"),result.getString("card_number"), result.getInt("card_owner_id"), Boolean.getBoolean(result.getString("is_blocked")), new Account(result.getInt("account_id"),result.getInt("account_number"), result.getFloat("balance") ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }
}
