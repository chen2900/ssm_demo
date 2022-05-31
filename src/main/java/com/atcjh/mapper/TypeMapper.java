package com.atcjh.mapper;

import com.atcjh.beans.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {

    //查询所有的字典类型
    List<Type> queryTypes();

    //根据id查询字典类型
    Type queryType(String id);

    //添加字典类型
    int insertType(@Param("type") Type type);

    //修改字典类型内容
    int updateType(@Param("type") Type type);

    //删除字典类型数据
    int deleteTypes(@Param("ids") String[] ids);


}