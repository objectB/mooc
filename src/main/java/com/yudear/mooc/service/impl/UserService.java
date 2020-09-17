package com.yudear.mooc.service.impl;

import com.yudear.mooc.entiy.Permission;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.mapper.PermissionMapper;
import com.yudear.mooc.mapper.RoleMapper;
import com.yudear.mooc.mapper.UserMapper;
import com.yudear.mooc.service.IUserService;
import com.yudear.mooc.vo.UserRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public User findUserById(int id) {
        User user = new User();
        user.setId(id);
        return  userMapper.selectOne(user);
    }

    @Override
    public UserRolePermission findUserRolePermission(int uId){
        UserRolePermission userRolePermission = new UserRolePermission();
        User user= userMapper.findUserById(uId);
        user.setPassword("");
        Role role = roleMapper.findRoleById(uId);
        List<Permission> permissionList= permissionMapper.findPermissionById(role.getId());
        userRolePermission.setUser(user);
        userRolePermission.setRole(role);
        userRolePermission.setPermissionList(permissionList);
        return userRolePermission;
    }
}
