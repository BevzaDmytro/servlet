package com.kpi.utils;

import com.google.gson.Gson;
import com.kpi.entities.User;

public class JsonMakerUtil {

    public static String createResponseJson(String token, User user, String message){
        String res;
        Gson gson = new Gson();
        res = " { \"token\": \""+ token + "\"  , \"user\" : " + gson.toJson(user) + " , \"message\": \" " +message + " \" }";
        return res;
    }

    public static String returnMessageGson(String msg){
//        return "{ \"message\" : \""+msg+"\"}";
        return "{ message : \""+msg+"\"}";
    }

    public static String userDataJson(User user){
        return "{ \"user\" : \""+new Gson().toJson(user)+"\"}";
    }

    public static String returnTokenJson(String s) {
        return "{ \"token\" : \""+s+"\"}";
    }
}
