package com.bjpowernode.mapper;

import com.bjpowernode.beans.Value;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ValueMapper {

    //查询所有字典值
    List<Value> queryValues();

    //添加字典值
    int insertValue(@Param("value") Value value);

    //根据id查询字典值
    Value queryById(String id);

    //修改字典值内容
    int updateValue(@Param("value") Value value);

    //根据字典类型的值查询数据
    List<String> checkDel(@Param("typeCodes") String[] typeCodes);

    //根据typeCode属性进行删除
    int deleteByTypeCode(@Param("typeCodes") String[] typeCodes);

    //删除字典值
    int deleteValue(@Param("ids") String[] ids);
}