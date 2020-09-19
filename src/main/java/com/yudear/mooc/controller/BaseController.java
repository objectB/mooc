package com.yudear.mooc.controller;

import com.alibaba.fastjson.JSON;
import com.yudear.mooc.common.response.RetCode;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", RetCode.UNAUTHORIZATI0N.code);
        map.put("msg", RetCode.UNAUTHORIZATI0N.msg);
        writeJson(map, response);
        return null;
    }

    private void writeJson(Map<String, Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSON.toJSONString(map));
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
