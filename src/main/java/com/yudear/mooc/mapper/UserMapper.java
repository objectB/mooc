package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.Role;
import com.yudear.mooc.entiy.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {


    @Select("select * from tab_user where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "username",column = "username"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "moto",column = "moto"),
            @Result(property = "email",column = "email"),
    })
    public User findUserById(int id);
}
