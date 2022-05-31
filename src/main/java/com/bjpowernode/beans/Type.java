package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * 字典类型表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {
    /**
    * 例如：sex、orgType
    */
    private String id;

    /**
    * 例如：性别、机构类型
    */
    private String name;

    /**
    * 对该字典类型的一个描述
    */
    private String description;

    private static final long serialVersionUID = 1L;
}