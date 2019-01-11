package com.luckyqing.sso.shiro.realm;

import com.luckyqing.sso.entity.TygSysUser;
import com.luckyqing.sso.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: notice
 * @description: noticeshiro框架的权限认证及授权
 * @author: "清歌"
 * @create: 2018-08-20 10:55
 **/
public class NoticeRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        TygSysUser userInfo  = (TygSysUser)principalCollection.getPrimaryPrincipal();
        List<String> authsUrlList = userService.findAuthsByUserId(userInfo.getId());
        if (authsUrlList != null && authsUrlList.size()> 0){
            for (int i = 0; i < authsUrlList.size(); i++) {
                authorizationInfo.addStringPermission(authsUrlList.get(i));
            }
        }
        return authorizationInfo;
    }


    /**
     * 用户验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 将token装换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        // 获取用户名即可
        String username = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        TygSysUser userInfo = userService.findUserByUserName(username);
        SimpleAuthenticationInfo info = null;
        if (userInfo != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object userPwd = userInfo.getUserPwd();
            // 获取盐值，即用户名
            //ByteSource salt = ByteSource.Util.bytes(username);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(userInfo, userPwd, realmName);
        } else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        return info;
    }


}
