package com.springboot.shiro;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject= SecurityUtils.getSubject();
      User  user=(User)subject.getPrincipal();
        //到数据库查询授权字符
       User dbuser=userService.findByid(user.getId());
       int jues=dbuser.getJues();
      String sf= Integer.toString(jues);
        //添加资源的授权字符串
        info.addStringPermission(sf);
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Autowired
    private UserService userService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写Shiro判断逻辑，判断用户名 密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
      User user=userService.findByName(token.getUsername());
        if (user==null){
            //用户名不存在
            return null; //Shiro底层会抛出UnknownAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
