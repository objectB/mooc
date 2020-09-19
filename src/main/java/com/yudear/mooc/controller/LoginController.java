package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.auth.utils.Md5Util;
import com.yudear.mooc.common.response.RetResponse;
import com.yudear.mooc.common.response.RetCode;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.ILoginService;
import com.yudear.mooc.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@Slf4j
public class LoginController  extends BaseController{

    @Autowired
    ILoginService iLoginService;

    @Autowired
    IUserService userService;

    @PostMapping(value = "/login")
    public RetResponse login(@RequestBody Map<String,String>  params) {
        String username = params.get("username");
        String password = params.get("password");
        if(username == null || password == null){
           return  RetResponse.error("用户名或密码不能为空");
        }

        String passwordMds = Md5Util.md5(password);
        User user = iLoginService.login(username, passwordMds);

        if(user.getStatus() == 1){
            return  RetResponse.error("账号被锁定");
        }

        user.setPassword("");
        Map<String,Object> map= userService.findUserRolePermission(user.getId());
        String token = JWTUtil.createToken(user.getId(),username);
        EhCacheUtil.getInstance().put(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY+user.getUsername(),token);
        map.put("token",token);
        map.put("user",user);

        return RetResponse.success(map);
    }

    @RequestMapping("/y")
    @RequiresRoles("admin")
    @RequiresPermissions(value = {"user:b"},logical = Logical.AND)
    public RetResponse yy(){
        return RetResponse.success("操作成功","1");
    }


}
