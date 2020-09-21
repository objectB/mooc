package com.yudear.mooc.controller.admin;

import com.yudear.mooc.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *  后台控制器
 */
@Controller
public class AdminController extends BaseController {

    @RequestMapping("/")
    public String index(){
        return "/admin/login/login";
    }

}
