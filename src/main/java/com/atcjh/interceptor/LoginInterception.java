package com.atcjh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器:进行登录的拦截
 */
public class LoginInterception implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先从session中取数据，如果有数据则表示已登录过并放行;如果无数据则表示未登录，拦截并跳转至登录页面
        Object user = request.getSession().getAttribute("user");
        if(user!=null){
            return true;
        }else {
            response.sendRedirect("/");
            return false;
        }
    }
}
