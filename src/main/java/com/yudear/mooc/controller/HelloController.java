package com.yudear.mooc.controller;

import com.yudear.mooc.common.model.R;
import com.yudear.mooc.common.model.UserUtil;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/index")
    public R index(){

        return  R.ok("xxx");

    }

}
