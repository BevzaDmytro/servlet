package com.kpi.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseCommand {
    private String page = "/";


    public abstract BaseCommand execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
    public final void executeRecursive(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        BaseCommand nextCommand = this.execute(req, resp);
        while (nextCommand != null)
            nextCommand = nextCommand.execute(req, resp);
    }
}
