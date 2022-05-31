package com.bjpowernode.service.impl;

import com.bjpowernode.beans.Type;
import com.bjpowernode.mapper.TypeMapper;
import com.bjpowernode.mapper.ValueMapper;
import com.bjpowernode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private ValueMapper valueMapper;


    @Override
    public List<Type> queryTypes() {
        return typeMapper.queryTypes();
    }

    @Override
    public Type queryTypeById(String id) {
        return typeMapper.queryType(id);
    }

    @Override
    public int insertType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    /**
     * 检查字典类型是引用，调用其他表中的查询语句确定是否能查询出数据，有数据则代表被引用，没有则代表没引用
     */
    @Override
    public List<String> checkDel(String[] ids) {
        return valueMapper.checkDel(ids);
    }

    @Override
    public int deleteTypes(String[] ids) {
        return typeMapper.deleteTypes(ids);
    }

    /**
     * 关联删除，先删除Value表中引用了该字典类型的数据，再删除Type表中的字典类型
     */
    @Override
    public int delForceType(String[] ids) {
        valueMapper.deleteByTypeCode(ids);
        //先不多考虑删除数量返回值的问题，随意返回一个数值
        return typeMapper.deleteTypes(ids);
    }


}
