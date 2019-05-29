package com.kpi.commands;

import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCardsCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String token = req.getHeader("auth");
        String token1 = req.getParameter("auth");


        int userId = UsersOnlineDao.getUserId(token1);

        UsersDao usersDao = new UsersDao();
        User user = usersDao.getById(userId);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setContentType("application/json");


        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.createResponseJson("1111",user, "msg");

        out.print(jsonObject);
        out.flush();

//        req.getRequestDispatcher(page).forward(req, resp);
        return null;
    }
}
