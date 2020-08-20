package com.wsw.shirolearn.config;


import com.wsw.shirolearn.entity.Permission;
import com.wsw.shirolearn.entity.Role;
import com.wsw.shirolearn.entity.User;
import com.wsw.shirolearn.exception.APIException;
import com.wsw.shirolearn.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description MyRealm配置
 * @Author wsw
 * @Date 2020/8/7 11:09
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 用户身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String userName = authenticationToken.getPrincipal().toString();
        User user = userService.getUser(userName);
        if (user == null) {
            throw new APIException(400,"验证用户信息错误");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
        return authenticationInfo;
    }

    /**
     * 权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthorizationException {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = null;
        try {
            user = userService.getRole(userName);
        } catch (Exception e) {
            throw new APIException(400,"验证用户信息错误");
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }
}
