package com.yudear.mooc.common.model;

import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {

    @Autowired
    IUserService iUserService;

    private static IUserService realService;

    @PostConstruct
    public void init() {
       realService = iUserService;
    }


    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Claims claims = (Claims) request.getAttribute("token");
        String subject = claims.getSubject();
        User currentUser = realService.findUserById(Integer.valueOf(claims.getId()));
        return currentUser == null ? new User():currentUser;
    }
}
