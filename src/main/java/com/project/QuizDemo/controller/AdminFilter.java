package com.project.QuizDemo.controller;

import com.project.QuizDemo.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;

        HttpSession session = httpRequest.getSession();
        User user = (User)session.getAttribute("user");

        if (user != null && user.isAdmin()) {
            chain.doFilter(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.write("Unauthenticated Action. Please log in if you are admin!");
            out.close();
        }
    }

}

