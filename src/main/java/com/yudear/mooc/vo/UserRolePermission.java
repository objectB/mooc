package com.yudear.mooc.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.yudear.mooc.entiy.Permission;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.entiy.User;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class UserRolePermission {


    private User user;
    private Role role;
    private List<Permission> permissionList = Collections.emptyList();



}
