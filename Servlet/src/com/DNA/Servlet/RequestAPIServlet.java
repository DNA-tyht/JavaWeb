package com.DNA.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/4/27 14:33
 */
public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("URI:" + req.getRequestURI());
        System.out.println("URL:" + req.getRequestURL());
        System.out.println("请求头:" + req.getHeader("User.Agent"));
    }
}
