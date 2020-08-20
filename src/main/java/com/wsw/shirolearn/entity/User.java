package com.wsw.shirolearn.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author wsw
 * @Date 2020/8/7 11:47
 * @Version 1.0
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private List<Role> roles;

}
