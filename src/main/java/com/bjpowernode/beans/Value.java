package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    * 字典值表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value implements Serializable {
    private String id;

    private String value;

    private String text;

    private Long orderNo;

    private String typeCode;

    private Type type;

    private static final long serialVersionUID = 1L;
}