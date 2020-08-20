package com.wsw.shirolearn.controller;

import com.wsw.shirolearn.entity.User;
import com.wsw.shirolearn.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description userController
 * @Author wsw
 * @Date 2020/8/7 15:44
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForGet() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginForPost(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            throw new AuthenticationException("用户名密码错误");
        }
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequiresRoles("admin")
    @GetMapping("/addUser")
    public String toAddUser() {
        return "addUser";
    }

    @RequiresPermissions("查看用户")
    @GetMapping("/showUser")
    public String toShowUser() {
        return "showUser";
    }

    @GetMapping("/500")
    public String error() {
        return "500";
    }

    @GetMapping("/logout")
    public String loginOut() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}
