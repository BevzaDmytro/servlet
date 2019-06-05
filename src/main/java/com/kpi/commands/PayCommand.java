package com.kpi.commands;

import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.model.UsersOnlineDao;
import com.kpi.services.PaymentsService;
import com.kpi.services.UserService;
import com.kpi.utils.JsonMakerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class PayCommand extends BaseCommand {
    @Override
    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String token = req.getHeader("Authorization");
        if(token == null) return null;

        String token1 = req.getParameter("auth");
        UserService userService = new UserService();

        User user = userService.getAuthorizedUser(token);
        String cardNumOwner = req.getParameter("senderCard");
        String cardNumRecip = req.getParameter("recipient");
        String amount = req.getParameter("amount");

        PaymentsService service = new PaymentsService(cardNumOwner,cardNumRecip);
        String result =  service.pay(amount);



        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.returnMessageGson(result);

        out.print(jsonObject);
        out.flush();
        return null;
    }
}
