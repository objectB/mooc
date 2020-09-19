package com.yudear.mooc.service.impl;

import com.yudear.mooc.common.utils.TreeUtils;
import com.yudear.mooc.entiy.MenuVo;
import com.yudear.mooc.mapper.MenuMapper;
import com.yudear.mooc.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class MenuService implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<MenuVo> findMenuVoByRoleId(int roleId) {

        List<MenuVo> menuVoByRoleId = menuMapper.findMenuVoByRoleId(roleId);

        menuVoByRoleId = TreeUtils.toTreeObject(menuVoByRoleId);

        return menuVoByRoleId == null ? new ArrayList<MenuVo>():menuVoByRoleId;

    }
}
