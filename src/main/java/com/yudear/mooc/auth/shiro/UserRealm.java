package com.yudear.mooc.auth.shiro;

import com.yudear.mooc.common.utils.SpringContextUtils;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.ILoginService;
import com.yudear.mooc.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ILoginService iLoginService;

    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String  username = (String) token.getPrincipal();
        if(iLoginService== null){
            iLoginService= SpringContextUtils.getBean(ILoginService.class);
        }
        User user = iLoginService.login(username);
        if(user == null){
            return  null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
