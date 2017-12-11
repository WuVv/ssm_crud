package com.panda.crud.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.panda.crud.bean.User;
import com.panda.crud.service.UserService;

public class MyRealm extends AuthorizingRealm{
    
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    
    @Autowired
    private UserService userService;

    /**
     * 用户授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName=(String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(userService.getRolesByName(userName));
        info.setStringPermissions(userService.getPermissionsByName(userName));
        return info;
    }

    /**
     * 用户登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户账号
        String userName = token.getPrincipal().toString();
        User user = userService.getUserByUserName(userName);
        if(user != null){
            // 盐值. 
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getId().toString());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), 
                    user.getPassword(), credentialsSalt, getName());
            return info;
        }
        return null;
    }

}
