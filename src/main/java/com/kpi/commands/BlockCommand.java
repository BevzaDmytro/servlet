package com.kpi.commands;

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

        UserService userService = new UserService();
        User user = userService.getAuthorizedUser(token1);

        CardsService service = new CardsService();
        service.block(cardNum);
//        service.block(cardToBlock);



        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setContentType("application/json");

        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.createResponseJson("1111",user, "Card is blocked");

        out.print(jsonObject);
        out.flush();

        return null;
    }
}
