package com.yudear.mooc.auth.shiro;

import com.alibaba.fastjson.JSON;
import com.yudear.mooc.auth.model.User;
import com.yudear.mooc.auth.utils.JWTUtil;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.xml.bind.DatatypeConverter;
import java.util.List;

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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
         JWTToken jwtToken = (JWTToken) authenticationToken;
         String  token = (String) jwtToken.getPrincipal();
        Claims claims=null;
        User user =new User();
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(
                            JWTUtil.configProperties.getSecret()))
                    .parseClaimsJws(token)
                    .getBody();
            user.setId(claims.getId());
            if(claims.get("roles") !=null) {
                user.setRoles((String) claims.get("roles"));
            }
        }catch (ExpiredJwtException e){
           throw new AuthenticationException("token过期"+e.getMessage());
        }catch (UnsupportedJwtException e){
            throw new AuthenticationException("token无效"+e.getMessage());
        }catch (MalformedJwtException e){
            throw new AuthenticationException("token格式错误"+e.getMessage());
        }catch (SignatureException e){
            throw new AuthenticationException("token签名无效"+e.getMessage());
        }catch (IllegalArgumentException e){
            throw new AuthenticationException("token参数异常"+e.getMessage());
        }catch (Exception e){
            throw new AuthenticationException("token无效"+e.getMessage());
        }
        System.out.println("认证完成");
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
       // String roles = user.getRoles();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("xxx");
      //  info.addRoles(JSON.parseArray(roles, String.class));
        System.out.println("授权完成");
        return info;
    }


}
