package com.yudear.mooc.service;

import com.yudear.mooc.entiy.User;
import com.yudear.mooc.vo.UserRolePermission;

import java.util.Map;

public interface IUserService {

    User findUserById(int id);

    Map<String,Object> findUserRolePermission(int uId);
}
