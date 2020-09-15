package com.yudear.mooc.service;

import com.yudear.mooc.entiy.User;

public interface IuserService {

    User login(String username, String password);
}
