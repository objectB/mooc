package com.yudear.mooc.service;

import com.yudear.mooc.model.User;

public interface IuserService {

    User login(String username, String password);
}
