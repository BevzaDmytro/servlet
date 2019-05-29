package com.kpi.model;

import com.kpi.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDao implements Dao {

    public User getById(int id){
        Connector connector = Connector.getInstance();
        String query = "Select u.name, u.email, u.password, u.is_admin, c.id as card_id, c.card_number, c.account_id, c.is_blocked, a.account_number, a.balance from users as u join cards as c on c.card_owner_id = u.id join accounts as a on a.id = c.account_id where u.id = " + id;
//TODO: допилити додавання карток і аккаунтів в юзера

        try {
            ResultSet result = connector.executeSQLWithReturn(query);
            result.next();
            String name = result.getString("name");
            String email = result.getString("email");
            String password = result.getString("password");
            String isAdmin = result.getString("is_admin");
            String cardNum = result.getString("card_number");
            int accountNum = result.getInt("account_number");
            float balance =  result.getFloat("balance");
            int cardId = result.getInt("card_id");
            int accountId = result.getInt("account_id");
            boolean isBlocked = Boolean.getBoolean(result.getString("is_blocked"));
            Card card =  new Card(cardId,cardNum,id, isBlocked, new Account(accountId,accountNum, balance));
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(card);

            while (result.next()){
                 cardNum = result.getString("card_number");
                 accountNum = result.getInt("account_number");
                 balance =  result.getFloat("balance");
                 cardId = result.getInt("card_id");
                 accountId = result.getInt("account_id");
                 card =  new Card(cardId,cardNum,id, isBlocked, new Account(accountId,accountNum, balance));
                cards.add(card);
            }




            return new User(id,name,email,password,Boolean.getBoolean(isAdmin), cards);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByLogin(String email, String password){
        Connector connector = Connector.getInstance();
        String query = "Select u.id as uid, u.name, u.email, u.password, u.is_admin, c.id as card_id, c.card_number, c.account_id, c.is_blocked, a.account_number, a.balance from users as u join cards as c on c.card_owner_id = u.id join accounts as a on a.id = c.account_id where email = '" + email + "' AND password = '" + password + "';";


        try {
            ResultSet result = connector.executeSQLWithReturn(query);
            result.next();
            String name = result.getString("name");
            int id = result.getInt("uid");
            String isAdmin = result.getString("is_admin");
            String cardNum = result.getString("card_number");
            int accountNum = result.getInt("account_number");
            float balance =  result.getFloat("balance");
            int cardId = result.getInt("card_id");
            int accountId = result.getInt("account_id");
            boolean isBlocked = Boolean.getBoolean(result.getString("is_blocked"));
            Card card =  new Card(cardId,cardNum,id, isBlocked, new Account(accountId,accountNum, balance));
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(card);

            while (result.next()){
                cardNum = result.getString("card_number");
                accountNum = result.getInt("account_number");
                balance =  result.getFloat("balance");
                cardId = result.getInt("card_id");
                accountId = result.getInt("account_id");
                card =  new Card(cardId,cardNum,id, isBlocked, new Account(accountId,accountNum, balance));
                cards.add(card);
            }

            return new User(id,name,email,password,Boolean.getBoolean(isAdmin), cards);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
