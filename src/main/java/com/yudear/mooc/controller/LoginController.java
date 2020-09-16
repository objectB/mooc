package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.EhCacheUtil;
import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.auth.utils.Md5Util;
import com.yudear.mooc.common.model.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
@Api(tags = "用户登录")
public class LoginController {

    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "手机号",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    public R  login(@RequestBody Map<String,String>  params){
        String username = params.get("username");
        String password = params.get("password");
        if(username == null || password == null){
           return  R.error("用户名或密码不能为空");
        }

        HashMap<String,Object> map = new HashMap<>();
        String s = Md5Util.md5("458686486");
        EhCacheUtil.getInstance().put(EhCacheUtil.TOKEN_CACHE,EhCacheUtil.USER_TOKEN_KEY,s);
        map.put("s",s);
        map.put("i",JWTUtil.generatorToken(s,""));
        return R.success("cxx",map);
    }

    @RequestMapping("/y")
    public R xx(){
        return  R.success("fefwwf");
    }


}
