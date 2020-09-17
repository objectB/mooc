package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.entiy.User;
import com.yudear.mooc.vo.UserRolePermission;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginMapper extends BaseMapper<User> {


    @Select("select * from tab_user where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "username",column = "username"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "moto",column = "moto"),
            @Result(property = "email",column = "email"),
            @Result(property = "role",column = "id",javaType = Role.class,
            one = @One(select = "com.yudear.mooc.mapper.RoleMapper.findRoleById")),
    })
    UserRolePermission findUserRolePermissionById(int id);

}