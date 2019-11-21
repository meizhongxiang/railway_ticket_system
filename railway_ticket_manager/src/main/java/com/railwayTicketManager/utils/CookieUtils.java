package com.railwayTicketManager.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: meizx
 * @ClassName: CookieUtils
 * @Date: 2019-07-04 16:04
 * @E-mail： meizxbj@gmail.com
 * @Description:
 * @Version: V1.0.0
 */
public class CookieUtils {
    public static Map<String,String> getCookie(Cookie[] cookies, String cookieName, String cookieId) {
        Map<String, String> map = new HashMap<>();
        //Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                map.put(cookieName,cookie.getValue());
            }
            if (cookie.getName().equals(cookieId)) {
                map.put(cookieId,cookie.getValue());
            }
        }
        return map;
    }


    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
           for (Cookie cookie : cookies) {
              if (cookie.getName().equals(cookieName)) {
                 return cookie.getValue();
              }
           }
        }
        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(3 * 60 * 60);
        response.addCookie(cookie);
    }
}
