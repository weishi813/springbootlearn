package com.wsw.shirolearn.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private int id;
    private String roleName;
    private List<Permission> permissions;
}
