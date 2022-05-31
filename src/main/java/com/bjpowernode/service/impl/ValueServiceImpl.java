package com.bjpowernode.service.impl;

import com.bjpowernode.beans.Value;
import com.bjpowernode.mapper.ValueMapper;
import com.bjpowernode.service.ValueService;
import com.bjpowernode.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueServiceImpl implements ValueService{

    @Autowired
    private ValueMapper valueMapper;

    @Override
    public List<Value> queryValues() {
        return valueMapper.queryValues();
    }

    @Override
    public int insertValue(Value value) {
        //根据UUID自动生成value表的id主键
        String uuid = UUIDUtils.getUUID();
        value.setId(uuid);
        return valueMapper.insertValue(value);
    }

    @Override
    public Value queryById(String id) {
        return valueMapper.queryById(id);
    }

    @Override
    public int updateValue(Value value) {
        return valueMapper.updateValue(value);
    }

    @Override
    public int deleteValue(String[] ids) {
        return valueMapper.deleteValue(ids);
    }
}
