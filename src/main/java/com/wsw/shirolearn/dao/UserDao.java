package com.wsw.shirolearn.dao;

import com.wsw.shirolearn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @Description userDao
 * @Author wsw
 * @Date 2020/8/7 11:47
 * @Version 1.0
 */
@Mapper
//@Repository
public interface UserDao {

    @Select("select * from user where userName=#{userName}")
    User getPassword(@Param("userName") String userName);

    User getRole(@Param("userName") String userName);

    @Select("select u.id,u.userName,u.password,r.*,p.id,p.permissionName,p.permissionUrl\n" +
            "          from `user` u,role r,permission p,user_role ur,role_permission rp WHERE u.id=ur.uId and ur.rId=r.id and\n" +
            "            r.id=rp.rId and p.id=rp.pId and u.userName=#{userName}")
    User getRole1(@Param("userName") String userName);
}