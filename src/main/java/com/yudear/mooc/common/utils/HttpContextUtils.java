package com.yudear.mooc.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 判断是否是Ajax请求
     * @return
     */
    public static boolean isAjaxRequest(){
        boolean isAjaxReq = false;
        String xRequestedWith = getHttpServletRequest().getHeader("X-Requested-With");

        // 判断是否为ajax请求
        isAjaxReq = StringUtils.isNotEmpty(xRequestedWith)
                && StringUtils.indexOf(xRequestedWith, "XMLHttpRequest") > -1;

        // json 请求头的也是ajax请求
        if (!isAjaxReq) {
            isAjaxReq = StringUtils.indexOf(getHttpServletRequest().getHeader("accept"), "application/json") > -1;
        }

        return isAjaxReq;
    }


}
