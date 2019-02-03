package com.logmei.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class CookieUtil {
    public static void set(HttpServletResponse response , String name , String value , Integer maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name){
        HashMap<String,Cookie> map = getCookieMap(request);
        if(map == null){
            return null;
        }else{
            return map.get(name);
        }
    }

    private static HashMap<String,Cookie> getCookieMap(HttpServletRequest request){
        HashMap<String,Cookie> map = new HashMap<>();
        if(request.getCookies()== null){
            return null;
        }
        for (Cookie cookie : request.getCookies()){
            map.put(cookie.getName(),cookie);
        }
        return map;
    }
}
