package com.lab.edu.shiro;

import com.lab.edu.model.Admin;
import com.lab.edu.model.User;
import com.lab.edu.service.AdminService;
import com.lab.edu.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ruin
 * @date 2019/7/12-9:48
 */
public class CommonRealm extends AuthorizingRealm{

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证逻辑");
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        Admin ad=adminService.getAdminByAccount(token.getUsername());
        if(ad==null){
            User user=userService.getUserByAccount(token.getUsername());
            if(user == null){
                System.out.println("用户不存在");
                return null;
            }
            return new SimpleAccount(user,user.getUserPassword(),"user");
        }

        return new SimpleAccount(ad,ad.getAdPassWord(),"admin");
    }
}
