package com.kpi.commands;

import com.kpi.entities.User;
import com.kpi.model.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginCommand extends BaseCommand {

    private String page = "pages/index.jsp";

    public BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        System.out.println("");
//        PrintWriter out = resp.getWriter();
//        out.print("<h1>Hello Servlet</h1>");


        if(req.getSession().getAttribute("name") != null){
            req.setAttribute("bean", req.getSession().getAttribute("user"));
            System.out.println("Name: " + req.getSession().getAttribute("name"));
            req.getRequestDispatcher("pages/main.jsp").forward(req, resp);
        }
        else {
            if (req.getParameter("email") != null) {

                String email = req.getParameter("email");
                String pw = req.getParameter("password");

                UsersDao usersDao = new UsersDao();
                User user = usersDao.getUserByLogin(email, pw);
                req.getSession().setAttribute("name", user.getName());
                req.getSession().setAttribute("user", user);
                System.out.println("Email: " + user.getEmail());
            }
        }


        req.getRequestDispatcher(page).forward(req, resp);
        return null;
    }

}
