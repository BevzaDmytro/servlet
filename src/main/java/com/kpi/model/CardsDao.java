package com.kpi.model;

import com.kpi.entities.Account;
import com.kpi.entities.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public Iterable<Card> getCardsByOwnerId(int id) {
        Connector connector = Connector.getInstance();
        String query = "Select c.id as card_id, c.card_number, c.account_id, c.is_blocked, a.account_number, a.balance from cards as c JOIN accounts as a ON c.account_id=a.id where  c.card_owner_id = " + id + ";";
        Iterable<Card> cards = new ArrayList<>();

        try {
            ResultSet result = connector.executeSQLWithReturn(query);
            while (result.next()){
            String cardNum = result.getString("card_number");
            int accountNum = result.getInt("account_number");
            float balance =  result.getFloat("balance");
            int cardId = result.getInt("card_id");
            int accountId = result.getInt("account_id");
            boolean isBlocked = Boolean.getBoolean(result.getString("is_blocked"));
            Card card =  new Card(cardId,cardNum,id, isBlocked, new Account(accountId,accountNum, balance));
            ((ArrayList<Card>) cards).add(card);
            }
            return cards;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
