package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.auth.utils.Md5Util;
import com.yudear.mooc.common.model.R;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    IuserService iuserService;

    @GetMapping("/unauthorized")
    public Object unauthorized(){
        Map<String,Object> map =  new HashMap<>();
        map.put("code",0);
        map.put("msg","未验证token");
        return  map;
    }

     @GetMapping("/log")
     public R  login(){
        String data = (String ) EhCacheUtil.getInstance().get(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY);
        return R.ok("登录成功",data);
    }

    @GetMapping("/yy")
    public R  yy(){
        HashMap<String,Object> map = new HashMap<>();
        String s = Md5Util.md5("458686486");
        //ClassUtils.getDefaultClassLoader().getResource("").getPath()+""
        EhCacheUtil.getInstance().put(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY,s);
        map.put("s",s);
        map.put("i",JWTUtil.generatorToken(s,""));
        return R.ok("cxx",map);
    }


}
