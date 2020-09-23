package com.yudear.mooc.controller.admin;

import com.yudear.mooc.common.model.UserUtil;
import com.yudear.mooc.controller.BaseController;
import com.yudear.mooc.entiy.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


/**
 *  后台控制器
 */
@Controller
public class AdminController extends BaseController {

    @RequestMapping("/")
    public String index() {
        User currentUser = UserUtil.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getId() != null) {
                return "forward:/index";
            } else {
                return "/admin/login/login";
            }
        }
        return "forward:/index";
    }
}
