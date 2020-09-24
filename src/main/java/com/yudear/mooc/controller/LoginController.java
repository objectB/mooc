package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.auth.utils.Md5Util;
import com.yudear.mooc.common.exception.BizException;
import com.yudear.mooc.common.response.RetResponse;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.ILoginService;
import com.yudear.mooc.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@Slf4j
public class LoginController  extends BaseController{

    @Autowired
    ILoginService iLoginService;

    @Autowired
    IUserService userService;


    @PostMapping(value = "/login")
    @ResponseBody
    public RetResponse login1(@RequestBody Map<String,String>  params) {
        String username = params.get("username");
        String password = params.get("password");
        if(username == null || password == null){
           throw  new BizException("用户名或密码不能为空~");
        }
        String passwordMds = Md5Util.md5(password);
        //用户认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =new UsernamePasswordToken(username,passwordMds);

        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
           throw  new BizException("账号不存在");
        }catch (AuthenticationException e){
            throw  new BizException("账号或密码错误");
        }
        return RetResponse.success("登录成功");
    }

    @RequestMapping("/logout")
    public String logOut(){
        SecurityUtils.getSubject().logout();
        return  "redirect:/";
    }


    @PostMapping(value = "/login1")
    @ResponseBody
    public RetResponse login(@RequestBody Map<String,String>  params,HttpSession session) {
        String username = params.get("username");
        String password = params.get("password");
        if(username == null || password == null){
           return  RetResponse.error("用户名或密码不能为空");
        }
//
//        String passwordMds = Md5Util.md5(password);
//        User user = iLoginService.login(username, passwordMds);
//
//        if(user.getStatus() == 1){
//            return  RetResponse.error("账号被锁定");
//        }
//
//        user.setPassword("");
//        Map<String,Object> map= userService.findUserRolePermission(user.getId());
//        String token = JWTUtil.createToken(user.getId(),username);
//        EhCacheUtil.getInstance().put(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY+user.getUsername(),token);
//        map.put("token",token);
//        map.put("user",user);



        return RetResponse.success();
    }

    @RequestMapping("/y")
    @RequiresRoles("admin")
    @RequiresPermissions(value = {"user:add"},logical = Logical.AND)
    public RetResponse yy(){
        return RetResponse.success("1");
    }





}
