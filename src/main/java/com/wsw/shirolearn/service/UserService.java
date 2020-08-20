package com.wsw.shirolearn.service;


import com.wsw.shirolearn.dao.UserDao;
import com.wsw.shirolearn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 获取用户数据
 * @Author wsw
 * @Date 2020/8/7 11:46
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(String userName) {
        return userDao.getPassword(userName);
    }

    public User getRole(String userName) {
        System.out.println("dao输出信息：" + userDao.getRole(userName).toString());
        return userDao.getRole(userName);
    }

    public User getRole1(String userName) {
        System.out.println("dao输出信息：" + userDao.getRole1(userName));
        return userDao.getRole1(userName);
    }

}
