package com.wsw.shirolearn.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Permission implements Serializable {
    private int id;
    private String permissionName;
    private String PermissionUrl;
}
