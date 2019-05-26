package com.kpi.model;

import com.kpi.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao implements Dao {

    public User getById(int id){
        Connector connector = Connector.getInstance();
        String query = "Select u.name, u.email, u.password, u.is_admin, c.card_number, c.account_id, a.balance from users as u join cards as c on c.card_owner_id = u.id join accounts as a on a.id = c.account_id where u.id = " + id;
//TODO: допилити додавання карток і аккаунтів в юзера

        try {
            ResultSet result = connector.executeSQL(query);
            result.next();

            String name = result.getString("name");
            String email = result.getString("email");
            String password = result.getString("password");
            String isAdmin = result.getString("is_admin");

            return new User(id,name,email,password,Boolean.getBoolean(isAdmin));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByLogin(String email, String password){
        Connector connector = Connector.getInstance();
        String query = "Select * from users where email = '" + email + "' AND password = '" + password + "';";


        try {
            ResultSet result = connector.executeSQL(query);
            result.next();

            int id = result.getInt("id");
            String name = result.getString("name");
//            String email = result.getString("email");
//            String password = result.getString("password");
            String isAdmin = result.getString("is_admin");

            return new User(id,name,email,password,Boolean.getBoolean(isAdmin));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
