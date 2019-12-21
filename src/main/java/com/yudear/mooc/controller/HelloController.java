package com.yudear.mooc.controller;

import com.yudear.mooc.auth.utils.JWTUtil;
import com.yudear.mooc.common.model.R;
import com.yudear.mooc.model.User;
import com.yudear.mooc.service.IUserService;
import com.yudear.mooc.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class HelloController {



    @RequestMapping("/index")
    public R index(){
        return  R.ok(UserUtil.getCurrentUser());

    }

}
