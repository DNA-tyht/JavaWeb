package com.DNA.servlet;

import com.DNA.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Currency;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/5/16 15:53
 */
public class CookieServlet extends BaseServlet{
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("key1", "value1");
        Cookie cookie2 = new Cookie("key2", "value2");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.getWriter().write("Cookie Create!");
    }

    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            response.getWriter().write(cookie.getName() + "<br/>");
        }
        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);
        if (iWantCookie != null) {
            response.getWriter().write("Find Cookie Success");
        }
    }

    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("key1", "newValue1");
        response.addCookie(cookie1);
        response.getWriter().write("Key1 Cookie Update" + "<br/>");

        Cookie cookie2 = CookieUtils.findCookie("key2", request.getCookies());
        if (cookie2 != null) {
            cookie2.setValue("newValue2");
            response.addCookie(cookie2);
            response.getWriter().write("Key2 Cookie Update");
        }
    }

    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        //live time
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        response.getWriter().write("Cookie Life -1");
    }

    protected void defaultNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultNow", "defaultNow");
        //live time
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.getWriter().write("Cookie Life 0");
    }

    protected void life3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        //live time
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        response.getWriter().write("Cookie Life 3600");
    }

    protected void testPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1", "path1");
        cookie.setPath(request.getContextPath() + "/abc");
        response.addCookie(cookie);
        response.getWriter().write("Path Cookie");
    }
}