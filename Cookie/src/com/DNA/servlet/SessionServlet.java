package com.DNA.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/5/17 8:25
 */
public class SessionServlet extends BaseServlet{
    protected void createOrGetSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建和获取Session对象
        HttpSession session = request.getSession();
        boolean aNew = session.isNew();
        String id = session.getId();
        response.getWriter().write(id);
        System.out.println(aNew);
    }

    protected void life(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3);
        session.invalidate();
    }
}
