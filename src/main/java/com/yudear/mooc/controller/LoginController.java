package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.common.model.R;
import com.yudear.mooc.model.User;
import com.yudear.mooc.service.IuserService;
import com.yudear.mooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
     public R  login(@RequestBody Map<String, String> map){
      //  List<String> list = Lists.list("admin", "super");

        if(map.get("name") == null || map.get("password") ==null){
            return R.error(500,"用户名密码不能为空");
        }

        User user = iuserService.login(map.get("name"), map.get("password"));
        if(user == null){
            return R.error(500,"用户名或密码错误");
        }
        String token = JWTUtil.generatorToken(map.get("name"),"");
        Map<String,String> data= new HashMap<>();
        data.put("token",token);
        return R.ok("登录成功",data);
    }

    @GetMapping("/yy")
    public R  yy(){

        return R.ok("cxx","hell wold");
    }


}
