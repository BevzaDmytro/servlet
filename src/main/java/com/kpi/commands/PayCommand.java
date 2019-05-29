package com.kpi.commands;

import com.kpi.entities.User;
import com.kpi.model.UsersDao;
import com.kpi.services.PaymentsService;
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

        System.out.println("PAY WORKS");
        String cardNumOwner = req.getParameter("senderCard");
        String cardNumRecip = req.getParameter("recipient");
        String amount = req.getParameter("amount");
        System.out.println(cardNumOwner);
        System.out.println(cardNumRecip);
        System.out.println(amount);

        PaymentsService service = new PaymentsService(cardNumOwner,cardNumRecip);
        String result =  service.pay(amount);

        UsersDao usersDao = new UsersDao();
//        User user = usersDao.getUserByLogin(email, pw);
        User user = usersDao.getById(1);

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setContentType("application/json");


        PrintWriter out = resp.getWriter();
        String jsonObject = JsonMakerUtil.createResponseJson("",user, result);

        out.print(jsonObject);
        out.flush();


        return null;
    }
}
