package com.project.QuizDemo.controller;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;

        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");

        if(httpRequest.getMethod().equalsIgnoreCase("POST")) {
            if ((username != null) && (password != null)) {
                chain.doFilter(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.write("Please enter Username and Password");
                out.close();
            }
        } else {
            chain.doFilter(request, response);
        }
    }



}
