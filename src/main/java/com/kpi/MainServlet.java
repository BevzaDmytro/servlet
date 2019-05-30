package com.kpi;

import com.kpi.commands.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private static final String COMMAND_REGEX = "^(?<command>/[a-z0-9]*)(\\?.{2,})?$";
    private Map<String, BaseCommand> commands;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");

    }

    public void init(){
        commands = new HashMap();
        commands.put("/", new GetCardsCommand());
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/pay", new PayCommand());
        commands.put("/block", new BlockCommand());
        commands.put("/unblock", new UnblockCommand());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String uri = req.getRequestURI();
        BaseCommand command = getCommandFromURI(uri);
        command.executeRecursive(req, resp);
        processRequest(req,resp);
    }

    private BaseCommand getCommandFromURI(String uri) {
        Pattern commandPattern = Pattern.compile(COMMAND_REGEX);
        Matcher matcher = commandPattern.matcher(uri);
        if (!matcher.matches())
            return null;

        String commandString = matcher.group("command");

        return commands.get(commandString);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {

    }
}
