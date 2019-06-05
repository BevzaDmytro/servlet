package com.kpi.commands;

import com.google.gson.Gson;
import com.kpi.entities.Card;
import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;
import com.kpi.services.CardsService;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetCardsCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String token = req.getHeader("Authorization");
//        System.out.println(token);
        String token1 = req.getParameter("auth");
        int userId = UsersOnlineDao.getUserId(token);

        CardsService cardsService = new CardsService();
        PrintWriter out = resp.getWriter();

        out.print(new Gson().toJson(cardsService.getCardsByOwnerId(userId)));
        out.flush();

        return null;
    }
}
