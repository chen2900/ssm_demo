package com.bjpowernode.service.impl;

import com.bjpowernode.beans.User;
import com.bjpowernode.exception.UserException;
import com.bjpowernode.mapper.UserMapper;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户的校验，用户名或密码是否正确以及账户是否锁定
     */
    public boolean checkUserStatus(User user){
        boolean flag = true;
        if(user == null){
            flag = false;
            throw new UserException("账号或密码错误!");
        }
        if("0".equals(user.getLockStatus())){
            flag = false;
            throw new UserException("账号被封禁，请联系管理员!");
        }
        return flag;
    }


    @Override
    public User queryUser(String username, String password) {
        //密码进行加密，跟数据库保持一致
        String md5Pwd = MD5Util.getMD5(password);
        User user = userMapper.queryUser(username, md5Pwd);
        boolean userStatus = checkUserStatus(user);
        if(!userStatus){
            return null;
        }
        return user;
    }

    @Override
    public User aotuLogin(String username, String password) {
        User user = userMapper.queryUser(username, password);
        boolean userStatus = checkUserStatus(user);
        if(!userStatus){
            return null;
        }
        return user;
    }

    @Override
    public void checkAndEditPwd(String id, String oldPwd, String newPwd) {
        //根据id获取被改密码账户的数据库信息
        User user = userMapper.queryUserByID(id);
        //对旧密码进行MD5加密，保持数据格式统一
        String md5OldPwd = MD5Util.getMD5(oldPwd);
        if (!user.getLoginPwd().equals(md5OldPwd)) {
            throw new UserException("原密码不正确");
        }
        //如果旧密码正确，则进行密码的修改
        String md5NewPwd = MD5Util.getMD5(newPwd);
        userMapper.updatePwd(id, md5NewPwd);
    }


}
