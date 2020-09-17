package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    @Select("select * from tab_role a left join tab_user_role b on a.id = b.role_id where b.user_id=#{id} ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "desc", column = "desc"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "sort", column = "sort"),
    })
    Role findRoleById(int id);
}
