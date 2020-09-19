package com.yudear.mooc.service.impl;


import com.yudear.mooc.entiy.MenuVo;
import com.yudear.mooc.entiy.Permission;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.mapper.PermissionMapper;
import com.yudear.mooc.mapper.RoleMapper;
import com.yudear.mooc.mapper.UserMapper;
import com.yudear.mooc.service.IMenuService;
import com.yudear.mooc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService implements IUserService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    IMenuService menuService;

    @Override
    public User findUserById(int id) {
        User user = new User();
        user.setId(id);
        return  userMapper.selectOne(user);
    }

    @Override
    public Map<String,Object> findUserRolePermission(int uId){
        Map<String, Object> userMap = new HashMap<>();
        Role role = roleMapper.findRoleById(uId);
        List<MenuVo> menuVoByRoleList = menuService.findMenuVoByRoleId(role.getId());
        List<Permission> permissionList = permissionMapper.findPermissionById(role.getId());

        userMap.put("permissions",permissionList);
        userMap.put("menus",menuVoByRoleList);
        userMap.put("roles", role);

        return userMap;
    }
}
