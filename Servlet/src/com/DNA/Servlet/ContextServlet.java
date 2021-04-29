package com.DNA.Servlet; /**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/4/26 22:23
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取web.xml配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        System.out.println("context-param参数username的值：" + username);
        System.out.println("context-param参数password的值：" + password);

        //获取当前工程路径，格式：/工程路径
        //System.out.println("当前工程路径：" + context.getContextPath());

        //获取工程部署后在服务器硬盘上的绝对路径
        // / 被服务器解析地址为http://ip:port/工程名/  映射到IDEA代码的web目录
        System.out.println("工程部署的路径：" + context.getRealPath("/"));
        System.out.println("工程下css目录的绝对路径：" + context.getRealPath("/css"));

        ServletContext context1 = getServletContext();
        context1.setAttribute("key1", "value1");
        System.out.println("key1:" + context1.getAttribute("key1"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}