package com.kpi.commands;

import com.google.gson.Gson;
import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;
import com.kpi.services.UserService;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginCommand extends BaseCommand {

    private String page = "pages/index.jsp";

    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String email = req.getParameter("email");

        String pw = req.getParameter("password");


        UserService userService = new UserService();
        User user =  userService.loginUser(email, pw);
        if(user == null) return null;

        String token = userService.generateToken();
        UsersOnlineDao.insertToken(user.getId(), token);


        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.returnTokenJson(token);

        out.print(jsonObject);
        out.flush();

        return null;
    }

}
