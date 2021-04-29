package com.DNA.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/4/29 18:59
 */
public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.getCharacterEncoding());
        //设置服务器字符集
        //resp.setCharacterEncoding("UTF-8");
        //设置浏览器字符集
        //resp.setHeader("Content-Type", "text/html; charset=UTF-8");

        //同时设置浏览器服务器的字符集
        //此方法要在获取流对象前使用
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("哦也");
    }
}
