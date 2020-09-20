package com.yudear.mooc.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.yudear.mooc.common.response.RetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InterfaceErrorController implements ErrorController {

    private static final String ERROR_PATH="/error";
    private ErrorAttributes errorAttributes;


    @Autowired
    public InterfaceErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes=errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    @RequestMapping(value=ERROR_PATH,produces="text/html")
    @ResponseBody
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
        ServletWebRequest requestAttributes =  new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        Map<String,Object> map = new HashMap<>();
        int  code =(int) attr.get("status");
        String msg =(String) attr.get("message");
        map.put("code",code);
        map.put("msg",msg);
        return JSONObject.toJSONString(map);
    }



    /**
     *  除Web页面外的错误处理 如JsonXlm
     * @param request
     * @return
     */
    @RequestMapping(value=ERROR_PATH)
    @ResponseBody
    public RetResponse errorApiHander(HttpServletRequest request) {
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr=this.errorAttributes.getErrorAttributes(requestAttributes, false);
        return RetResponse.error((int)attr.get("status"),(String) attr.get("message"));

    }
}
