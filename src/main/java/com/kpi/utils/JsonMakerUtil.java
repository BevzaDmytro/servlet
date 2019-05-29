package com.kpi.utils;

import com.google.gson.Gson;
import com.kpi.entities.User;

public class JsonMakerUtil {

    public static String createResponseJson(String token, User user, String message){
        String res;
        Gson gson = new Gson();
        res = " { \"user\" : " + gson.toJson(user) + " , \"message\": \" " +message + " \" }";
        return res;
    }
}
