package com.kpi.commands;

import com.kpi.model.UsersOnlineDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String token1 = req.getParameter("auth");
//        String token1 = req.getHeader("auth");
//        Enum stringEnum = (Enum) req.getHeaderNames();
//        System.out.println(stringEnum.toString());
//        System.out.println(token1);
        UsersOnlineDao.deleteToken(token1);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Max-Age", "3600");
        return null;
    }
}
