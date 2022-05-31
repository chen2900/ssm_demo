package com.atcjh.service;

import com.atcjh.beans.Value;

import java.util.List;

public interface ValueService{

    //查询所有字典值
    List<Value> queryValues();

    //添加字典值
    int insertValue(Value value);

    //根据id查询字典值
    Value queryById(String id);

    //修改字典值内容
    int updateValue(Value value);

    //删除字典值
    int deleteValue(String[] ids);

}
