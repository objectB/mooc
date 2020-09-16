package com.yudear.mooc.service;


import com.yudear.mooc.auth.model.User;

public interface ILoginService {


    User login(String username,String password);
}
