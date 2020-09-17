package com.yudear.mooc.service;

import com.yudear.mooc.entiy.User;
import com.yudear.mooc.vo.UserRolePermission;

public interface IUserService {

    User findUserById(int id);

    UserRolePermission findUserRolePermission(int uId);
}
