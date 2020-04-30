package com.yudear.mooc.auth.shiro;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.common.exception.BizException;
import com.yudear.mooc.common.model.R;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JWTFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //System.out.println("检查token是否合法");
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        String token = request.getHeader("jwt");
        if (StringUtils.isEmpty(token)) {
            String msg = JSON.toJSONString(R.error(500, "token不能为空"));
            response.getWriter().print(msg);
            return false;
        }
        JWTToken jwtToken = null;
        try {
            jwtToken = new JWTToken(token);
            Subject subject = SecurityUtils.getSubject();
            subject.login(jwtToken);
        } catch (AuthenticationException e) {
            String msg = JSON.toJSONString(R.error(500, e.getMessage()));
            response.getWriter().print(msg);
            return false;
//        } catch (UnsupportedJwtException e) {
//            String msg = JSON.toJSONString(R.error(500, "token无效"));
//            response.getWriter().print(msg);
//            return false;
//        } catch (MalformedJwtException e) {
//            String msg = JSON.toJSONString(R.error(500, "token格式错误"));
//            response.getWriter().print(msg);
//            return false;
//        } catch (SignatureException e) {
//            String msg = JSON.toJSONString(R.error(500, "token签名错误"));
//            response.getWriter().print(msg);
//            return false;
//        } catch (IllegalArgumentException e) {
//            String msg = JSON.toJSONString(R.error(500, "token参数异常"));
//            response.getWriter().print(msg);
//            return false;
//        }
//        catch (Exception e) {
//            String msg = JSON.toJSONString(R.error(500, "token出错"));
//            response.getWriter().print(msg);
//            return false;
//        }
        }
        request.setAttribute("token", JWTUtil.getClaimByToken(token));
        return true;
    }


}
