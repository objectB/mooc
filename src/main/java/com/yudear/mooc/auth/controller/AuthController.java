package com.yudear.mooc.auth.controller;

import com.alibaba.fastjson.JSON;
import com.yudear.mooc.auth.shiro.JWTToken;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.common.model.R;
import com.yudear.mooc.model.User;
import com.yudear.mooc.service.IUserService;
import com.yudear.mooc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/unauthorized")
    public Object unauthorized(){
        Map<String,Object> map =  new HashMap<>();
        map.put("code",0);
        map.put("msg","未验证token");
        return  map;
    }

    @PostMapping("/login")
     public R  login(@RequestBody Map<String, String> map){
      //  List<String> list = Lists.list("admin", "super");

        if(map.get("name") == null || map.get("password") ==null){
            return R.error(500,"用户名密码不能为空");
        }
        User user = userService.login(map.get("name"), map.get("password"));
        if(user == null){
            return R.error(500,"用户名或密码错误");
        }
        String token = JWTUtil.generatorToken(map.get("name"),"");
        return R.ok("登录成功",token);
    }

    @GetMapping("/yy")
    public R  yy(){

        return R.ok("cxx","hell wold");
    }


}
