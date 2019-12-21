package com.yudear.mooc.auth.shiro;

import com.alibaba.druid.util.StringUtils;
import com.yudear.mooc.auth.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JWTFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //System.out.println("检查token是否合法");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("jwt");
        if(StringUtils.isEmpty(token)){
            System.out.println("token不存在");
            return  false;
        }
        JWTToken jwtToken = new JWTToken(token);
        Subject subject = SecurityUtils.getSubject();
        subject.login(jwtToken);

        request.setAttribute("token", JWTUtil.getClaimByToken(token));
        return true;
    }
}
