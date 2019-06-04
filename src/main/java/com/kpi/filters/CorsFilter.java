package com.kpi.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

public class CorsFilter implements Filter {

    public CorsFilter(){

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Max-Age", "3600");
        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }



    @Override
    public void destroy() {

    }
}