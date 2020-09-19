package com.yudear.mooc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yudear.mooc.entiy.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 菜单
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuVo> {

    @Select("select * from tab_permission menu left join tab_role_permission role on menu.id =role.permission_id where menu.type=1 and  role.role_id =#{roleId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "name", column = "name"),
            @Result(property = "path", column = "path"),
            @Result(property = "hidden", column = "hidden"),
            @Result(property = "typename", column = "typename"),

    })
    List<MenuVo> findMenuVoByRoleId(int roleId);
}
