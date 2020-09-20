package com.yudear.mooc.auth.shiro;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.common.response.RetCode;
import com.yudear.mooc.common.response.RetResponse;
import com.yudear.mooc.common.utils.Constants;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        if (((HttpServletRequest) servletRequest).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = request.getHeader("Authorization");
        String paramToken = (String) request.getParameter("token");

        if(Constants.NO_NEED_FILTER.contains(request.getServletPath())){
            return  true;
        }

        if (StringUtils.isEmpty(token)  && StringUtils.isEmpty(paramToken) ) {
            String msg = JSON.toJSONString(RetResponse.error(RetCode.UNUSERTOKEN));
            response.getWriter().print(msg);
            response.getWriter().close();
            return false;
        }

        String loginToken = "";
        if(!StringUtils.isEmpty(token)){
            loginToken = token;
        }
        if(!StringUtils.isEmpty(paramToken)){
            loginToken = paramToken;
        }

        JWTToken jwtToken = null;
        try {
            jwtToken = new JWTToken(loginToken);
            Subject subject = SecurityUtils.getSubject();
            subject.login(jwtToken);
        } catch (AuthenticationException e) {
            String msg = JSON.toJSONString(RetResponse.error(RetCode.UNAUTHORIZED.code,e.getMessage()));
            response.getWriter().print(msg);
            response.getWriter().close();
            return false;
        }
        request.setAttribute("token", JWTUtil.getClaimByToken(loginToken));
        return true;
    }


    /**
     * 对跨域提供支持
     */
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
//        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }


}
