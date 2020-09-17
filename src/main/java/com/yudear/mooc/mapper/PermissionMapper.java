package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.Permission;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select * from tab_permission a left join tab_role_permission b on a.id =b.permission_id where b.role_id =#{rid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "name", column = "name"),
            @Result(property = "path", column = "path"),
            @Result(property = "type", column = "type"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "hidden", column = "hidden"),
    })
    List<Permission>  findPermissionById(int rid);
}
