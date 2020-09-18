package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.auth.utils.Md5Util;
import com.yudear.mooc.common.model.R;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.ILoginService;
import com.yudear.mooc.service.IUserService;
import com.yudear.mooc.vo.UserRolePermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class LoginController {

    @Autowired
    ILoginService iLoginService;

    @Autowired
    IUserService userService;

    @PostMapping(value = "/login")
    public R  login(@RequestBody Map<String,String>  params){
        String username = params.get("username");
        String password = params.get("password");
        if(username == null || password == null){
           return  R.error("用户名或密码不能为空");
        }

        String passwordMds = Md5Util.md5(password);
        User user = iLoginService.login(username, passwordMds);
        Map<String,Object> map= userService.findUserRolePermission(user.getId());

        String token = JWTUtil.createToken(user.getId(),user.getUsername());
        EhCacheUtil.getInstance().put(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY+user.getUsername(),token);
        map.put("token",token);

        return R.success("操作成功",map);
    }

    @RequestMapping("/y")
    @RequiresRoles("admin1")
    public R yy(){
        return R.success("操作成功","1");
    }


}
