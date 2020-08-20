package com.wsw.shirolearn.config;


import com.wsw.shirolearn.util.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Description 自定义密码校验规则
 * @Author wsw
 * @Date 2020/8/7 11:18
 */
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        String password = MD5Util.getMd5(String.valueOf(passwordToken.getPassword()));
        String sqlPassword = info.getCredentials().toString();
        return this.equals(password, sqlPassword);
    }

}
