package com.bjpowernode.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String id;

    private String loginAct;

    private String name;

    private String loginPwd;

    /**
    * 0表示锁定1表示启用
    */
    private String lockStatus;

    private static final long serialVersionUID = 1L;
}