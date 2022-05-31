package com.atcjh.controller;

import com.atcjh.beans.User;
import com.atcjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录、视图跳转控制器
 */
@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    /**
     * 打开网址自动跳转至登录页面
     */
    @RequestMapping("/")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        String password = null;
        //判断是否有Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            //循环取出cookie里面的用户名和密码的值
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    //说明此次循环Cookie里面有一个为username的cookie数据
                    username = cookies[i].getValue();
                }
                if (cookies[i].getName().equals("password")) {
                    //说明此次循环Cookie里面有一个为password的cookie数据
                    password = cookies[i].getValue();
                }
            }
        }
        if (username != null && password != null) {
            //如果值都没问题，进行自动登录
            User user = userService.aotuLogin(username, password);
            if (user != null) {
                //说明账号密码都正确，查到了数据，重新设定session
                request.getSession().setAttribute("user", user);
                //更新cookie值
                int time = 60 * 60 * 24 * 10;
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(time);
                //设置Cookie使用的路径位置->当你请求你设置的这个路径的时候，应用cookie
                usernameCookie.setPath("/");
                Cookie passwordCookie = new Cookie("password", password);
                passwordCookie.setMaxAge(time);
                passwordCookie.setPath("/");
                //将cookie添加到响应对象中，会自动保存至浏览器硬盘
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                //根据路径截取方法，进行页面的跳转
                return "redirect:/settings/dictionary/index.html";
            }
        }
        return "login";
    }

    /**
     * 因为所有的页面都在/WEB-INF下，重定向不能访问该目录的页面，
     * 所以此方法通过截取请求中的路径，为了程序中单纯的进入到/WEB-INF下的jsp页面
     */
    @RequestMapping("/**/*.html")
    public String subHTML(HttpServletRequest request) {
        //URI,请求的相对路径
        String requestURI = request.getRequestURI();
        return requestURI.substring(1, requestURI.lastIndexOf("."));
    }
}
