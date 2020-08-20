package com.wsw.shirolearn.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Description shiro配置
 * @Author wsw
 * @Date 2020/8/7 10:51
 */
@Configuration
public class ShiroConfig {

    /**
     * shiroFilterFactoryBean是整个Shiro的入口点，用于拦截需要安全控制的请求进行处理
     * 进行全局配置，Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager 依赖于下面的bean，按名称注入
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//        map.put("/logout","logout");
        map.put("/login", "anon");
        map.put("/500", "anon");
        map.put("/**", "authc");
        bean.setLoginUrl("/login");
        //TODO 这两个设置无法进行跳转，原因未知
        /*bean.setUnauthorizedUrl("/500");
        bean.setSuccessUrl("/index");*/
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * 桥梁，主要是Realm的管理认证配置
     *
     * @param myRealm 依赖于下面的bean，按名称注入
     * @return
     */
    @Bean
    public SecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * @param credentialsMatcher 依赖于下面的bean，按名称注入
     * @return
     */
    @Bean
    public MyRealm myRealm(CredentialsMatcher credentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    /**
     * 自定义密码验证规则
     *
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new MyCredentialsMatcher();
    }

    //TODO 使用shiro的加密设置
    /**
     *@Bean
     *    public HashedCredentialsMatcher hashedCredentialsMatcher() {
     * 		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
     * 		hashedCredentialsMatcher.setHashAlgorithmName("MD5");//采用MD5 进行加密
     * 		hashedCredentialsMatcher.setHashIterations(1);//加密次数
     * 		return hashedCredentialsMatcher;
     *    }
     *
     * @Bean
     *    public AuthorizingRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
     * 		MyShiroRealm shiroRealm = new MyShiroRealm();
     * 		//校验密码用到的算法
     * 		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
     * 		return shiroRealm;
     *    }
     */

    /**
     * 管理Shiro中一些bean的生命周期
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    //TODO 启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
    // 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能

    /**
     * 扫描上下文，寻找所有的Advistor(通知器）
     * 将这些Advisor应用到所有符合切入点的Bean中
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 启用shiro注解
     *
     * @param securityManager 上面的bean，自动注入
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

}
