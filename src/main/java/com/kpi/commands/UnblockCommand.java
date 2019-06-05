package com.kpi.commands;

import com.kpi.entities.User;
import com.kpi.services.CardsService;
import com.kpi.services.UserService;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UnblockCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String cardNum = req.getParameter("unblockCard");
        String token1 = req.getParameter("auth");
        String token = req.getHeader("Authorization");
        if(token == null) return null;

        UserService userService = new UserService();
        User user = userService.getAuthorizedUser(token);
        if(!userService.checkIsAdmin(user)){
            return null;
        }

        CardsService service = new CardsService();
        service.unblock(cardNum);

        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.returnMessageGson("Card unblocked successful");

        out.print(jsonObject);
        out.flush();

        return null;
    }
}
