package com.atcjh.service;

import com.atcjh.beans.User;

public interface UserService{

    //根据账号密码进行查询用户
    User queryUser(String username, String password);

    //根据Cookie里面的值自动登录
    User aotuLogin(String username,String password);

    //检查旧密码是否正确，并根据结果进行密码的修改
    void checkAndEditPwd(String id, String oldPwd, String newPwd);
}
