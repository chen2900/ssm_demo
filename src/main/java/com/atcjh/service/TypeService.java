package com.atcjh.service;

import com.atcjh.beans.Type;

import java.util.List;

public interface TypeService{

    //查询所有的字典类型
    List<Type> queryTypes();

    //根据id查询字典类型
    Type queryTypeById(String id);

    //添加字典类型
    int insertType(Type type);

    //修改字典类型内容
    int updateType(Type type);

    //根据id检查字典类型是否被引用
    List<String> checkDel(String[] ids);

    //删除字典类型
    int deleteTypes(String[] ids);

    //同时删除字典类以及对应的字典值
    int delForceType(String[] ids);

}
