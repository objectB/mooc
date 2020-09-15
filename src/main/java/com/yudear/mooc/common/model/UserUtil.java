package com.yudear.mooc.common.model;

import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.IuserService;
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
    IuserService userService1;

    private static IuserService realService;

    @PostConstruct
    public void init() {
        realService = userService1;
    }


    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Claims claims = (Claims) request.getAttribute("token");
        String subject = claims.getSubject();
        User currentUser = realService.login(subject, null);
        return currentUser;

    }

}
