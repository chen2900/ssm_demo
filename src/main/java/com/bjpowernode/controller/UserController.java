package com.bjpowernode.controller;

import com.bjpowernode.beans.User;
import com.bjpowernode.exception.UserException;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param username  用户名
     * @param password  密码
     * @param autoLogin 是否勾选十天免登录
     * @return 给ajax请求返回提示信息
     */
    @RequestMapping("login.do")
    @ResponseBody
    public Map login(String username,
                     String password,
                     boolean autoLogin,
                     HttpServletRequest request,
                     HttpServletResponse response) {
        Map map = new HashMap<>();
        User user = null;
        //抓取登录时的自定义异常，如果有异常，将错误信息提示给前端用户
        try {
            user = userService.queryUser(username, password);
        } catch (UserException e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
        }
        //走到这里说明用户正确，存储到服务器会话session中
        request.getSession().setAttribute("user", user);
        //判断是否10天免登录，不管账号是否正确，存储到cookie
        if (autoLogin) {
            int time = 60 * 60 * 24 * 10;
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(time);
            //设置Cookie使用的路径位置->当你请求你设置的这个路径的时候，应用cookie
            usernameCookie.setPath("/");
            String md5Pwd = MD5Util.getMD5(password);
            Cookie passwordCookie = new Cookie("password", md5Pwd);
            passwordCookie.setMaxAge(time);
            passwordCookie.setPath("/");
            //将cookie添加到响应对象中，会自动保存至浏览器硬盘
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 进行修改密码，由于这里前端的请求是单击事件，不是ajax，就没有进行旧密码错误的提示了(自己知道就好)
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    @RequestMapping("changePwd.do")
    public String updatePwd(String oldPwd, String newPwd, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        userService.checkAndEditPwd(user.getId(), oldPwd, newPwd);
        //修改成功，清除session数据/(修改失败的话，会直接程序异常，这里并没有进行异常的处理)
        request.getSession().invalidate();
        //清除cookie
        Cookie usernameCookie = new Cookie("username", null);
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        Cookie passwordCookie = new Cookie("password", null);
        passwordCookie.setMaxAge(0);
        passwordCookie.setPath("/");
        response.addCookie(passwordCookie);
        return "login";
    }

    /**
     * 退出系统登录的请求
     */
    @RequestMapping("login.html")
    public String exitLogin(HttpServletRequest request, HttpServletResponse response) {
        //清除session数据
        request.getSession().invalidate();
        //清除cookie
        Cookie usernameCookie = new Cookie("username", null);
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
        Cookie passwordCookie = new Cookie("password", null);
        passwordCookie.setMaxAge(0);
        passwordCookie.setPath("/");
        response.addCookie(passwordCookie);
        return "login";
    }
}
