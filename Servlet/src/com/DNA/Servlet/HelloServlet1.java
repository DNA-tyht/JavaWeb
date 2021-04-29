package com.DNA.Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/4/26 11:32
 */
public class HelloServlet1 implements Servlet {
    public HelloServlet1() {
        System.out.println("构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init方法");

        System.out.println("HelloServlet程序的别名：" + servletConfig.getServletName());
        System.out.println("初始化参数username：" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数URL：" + servletConfig.getInitParameter("user"));
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * @Description service方法专门用来处理请求和响应
     * @Return [servletRequest, servletResponse]
     * @Author 脱氧核糖
     * @Date 2021/4/26 11:36
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("HelloServlet被访问");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获取请求的方法
        String method = httpServletRequest.getMethod();
        System.out.println(method);
        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
            doPost();
        }
    }

    public void doGet() {
        System.out.println("get请求");
    }

    public void doPost() {
        System.out.println("post请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");
    }
}