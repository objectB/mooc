package com.yudear.mooc.controller;

import com.yudear.mooc.common.model.UserUtil;
import com.yudear.mooc.entiy.MenuVo;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private IUserService iUserService;

    @ModelAttribute(name = "menu")
    public List<MenuVo> menuVoList(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user != null) {
            Map<String, Object> map = iUserService.findUserRolePermission(user.getId());
            List<MenuVo> menuVoList = (List<MenuVo>) map.get("menus");
            return menuVoList;
        }
        return null;
    }

    @ModelAttribute(name = "user")
    public User user(){
        return UserUtil.getUser();
    }

}
