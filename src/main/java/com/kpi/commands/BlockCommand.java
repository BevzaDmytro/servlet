package com.kpi.commands;

import com.google.gson.Gson;
import com.kpi.entities.Card;
import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;
import com.kpi.services.CardsService;
import com.kpi.services.UserService;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BlockCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String cardNum = req.getParameter("cardToBlock");
        String token1 = req.getParameter("auth");

//        UserService userService = new UserService();
//        User user = userService.getAuthorizedUser(token1);

        CardsService service = new CardsService();
        service.block(cardNum);
//        service.block(cardToBlock);

        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.returnMessageGson("Card is blocked");

        out.print(jsonObject);
        out.flush();

        return null;
    }
}
