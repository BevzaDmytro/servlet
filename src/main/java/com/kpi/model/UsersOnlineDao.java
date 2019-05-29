package com.kpi.model;

import com.kpi.entities.User;
import com.kpi.entities.UsersOnline;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersOnlineDao {

    public static int getUserId(String token){
        Connector connector = Connector.getInstance();
        String query = "Select user_id from users_online where token = '" + token+"'";


        try {
            ResultSet result = connector.executeSQLWithReturn(query);
            result.next();

            return result.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void insertToken(int id, String token){
        Connector connector = Connector.getInstance();
        String query = "INSERT INTO users_online (user_id, token) VALUES("+id+", '" +token + "')";
             connector.executeSQL(query);


    }

    public static void deleteToken( String token){
        Connector connector = Connector.getInstance();
        String query = "DELETE FROM  users_online WHERE token = '"+token+"'";
        connector.executeSQL(query);

    }
}
