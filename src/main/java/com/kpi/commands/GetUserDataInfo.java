package com.kpi.commands;

import com.google.gson.Gson;
import com.kpi.entities.User;
import com.kpi.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetUserDataInfo extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String token = req.getHeader("Authorization");
        String token1 = req.getParameter("auth");
        if(token == null) return null;
        UserService userService = new UserService();
        User user =  userService.getAuthorizedUser(token);
        System.out.println(user.getName());
        if(user == null) return null;


        PrintWriter out = resp.getWriter();

        out.print(new Gson().toJson(user));
        out.flush();


        return null;
    }
}
