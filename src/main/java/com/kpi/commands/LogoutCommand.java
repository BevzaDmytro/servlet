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
        String token = req.getHeader("Authorization");

        UsersOnlineDao.deleteToken(token);

        return null;
    }
}
