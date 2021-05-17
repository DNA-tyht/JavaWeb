package com.DNA.utils;

import javax.servlet.http.Cookie;

/**
 * @Description
 * @Author 脱氧核糖
 * @Version 1.0
 * @Date 2021/5/16 20:59
 */
public class CookieUtils {
    /**
    * @Description 查找指定名称的Cookie对象
    * @Return [name, cookies]
    * @Author 脱氧核糖
    * @Date 2021/5/16 21:02
    */
    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
