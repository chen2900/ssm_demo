package com.bjpowernode.mapper;

import com.bjpowernode.beans.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //根据账号密码进行查询用户
    User queryUser(@Param("loginAct") String username, @Param("loginPwd") String password);

    //根据id查询用户
    User queryUserByID(String id);

    //根据id修改密码
    int updatePwd(@Param("id") String id,@Param("newPwd") String newPwd);
}