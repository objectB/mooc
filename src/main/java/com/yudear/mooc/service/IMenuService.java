package com.yudear.mooc.service;

import com.yudear.mooc.entiy.MenuVo;

import java.util.List;

public interface IMenuService {

    List<MenuVo> findMenuVoByRoleId(int roleId);
}
