package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.Permission;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select * from tab_permission a left join tab_role_permission b on a.id =b.permission_id where a.type=2 and  b.role_id =#{rid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typename", column = "typename"),
            @Result(property = "permissionname", column = "permissionname"),
    })
    List<Permission>  findPermissionById(int rid);
}
