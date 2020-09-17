package com.yudear.mooc.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yudear.mooc.common.exception.BizException;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.mapper.LoginMapper;
import com.yudear.mooc.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements ILoginService {

    @Autowired
    LoginMapper userMapper;

    @Override
    public User login(String username, String password) {
        EntityWrapper<User> queryWrapper = new EntityWrapper<>();
        queryWrapper.eq("username",username).and().eq("password",password);

        //userMapper.select
        List<User> users = userMapper.selectList(queryWrapper);
        if(users == null)
            throw new BizException("找不到用户");

        return  users.get(0);
    }
}
