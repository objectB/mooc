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
        User user = UserUtil.getUser();
        if(user != null){
            return "redirect:/admin/index";
        }
        return "/admin/login/login";
    }

    @RequestMapping("/admin/index")
    public String  defaultPage(){
        return "/admin/default/index";
    }


    @RequestMapping("/user/info")
    public String  moves(){
        return "/admin/test1";
    }



    @RequestMapping("/user/update")
    public String  update(){
        return "/admin/test1";
    }

}
