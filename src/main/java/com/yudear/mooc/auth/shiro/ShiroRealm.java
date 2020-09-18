package com.yudear.mooc.auth.shiro;

import com.alibaba.fastjson.JSON;
import com.yudear.mooc.auth.model.User;
import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.entiy.Permission;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.service.IUserService;
import com.yudear.mooc.service.impl.UserService;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;


public class ShiroRealm extends AuthorizingRealm {


    @Override
    public Class getAuthenticationTokenClass() {
        return JWTToken.class;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
     throws AuthenticationException{
         JWTToken jwtToken = (JWTToken) authenticationToken;
         String  token = (String) jwtToken.getPrincipal();
         User user =new User();

         try{
             Claims  claims = Jwts.parser()
                     .setSigningKey(DatatypeConverter.parseBase64Binary(
                             JWTUtil.configProperties.getSecret()))
                     .parseClaimsJws(token)
                     .getBody();
             user.setId(claims.getId());
             user.setUsername(claims.getSubject());
             String old =(String) EhCacheUtil.getInstance().get(EhCacheUtil.TOKEN_CACHE,
                     EhCacheUtil.USER_TOKEN_KEY+user.getUsername());
             if(old != null && !old.equals(token)){
                throw  new AuthenticationException("token失效");
             }
         }catch (ExpiredJwtException e){
            // throw  new AuthenticationException("token过期");
         }catch (UnsupportedJwtException e){
             throw  new AuthenticationException("token无效");
         }catch (MalformedJwtException e){
             throw  new AuthenticationException("token格式错误");
         }catch (SignatureException e ){
             throw  new AuthenticationException("token错误");
         }

        return new SimpleAuthenticationInfo(user,Boolean.TRUE,getName());

    }


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取主凭证信息
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getUsername());

        return info;
    }


}
