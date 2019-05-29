package com.kpi.model;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Connector {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/payments?useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    // JDBC variables for opening and managing connection
    private java.sql.Connection con;
    private  java.sql.Statement stmt;
    private static Connector instance = null;

    public static Connector getInstance(){
        if(instance == null){
            instance = new Connector();
        }
        return instance;
    }

    private Connector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet executeSQLWithReturn(String query){
        try {
//            stmt = (Statement) con.createStatement();
            stmt =  con.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void executeSQL(String query){
        try {
//            stmt = (Statement) con.createStatement();
            stmt =  con.createStatement();
             stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    public static void main(String args[]) {
//        String query = "select count(*) from books";
//
//        try {
//
//            // executing SELECT query
//            rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println("Total number of books in the table : " + count);
//            }
//
//        } catch (SQLException sqlEx) {
//            sqlEx.printStackTrace();
//        } finally {
//            //close connection ,stmt and resultset here
//            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
//            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
//        }
//    }
}
