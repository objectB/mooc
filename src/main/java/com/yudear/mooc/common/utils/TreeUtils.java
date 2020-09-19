package com.yudear.mooc.common.utils;

import com.yudear.mooc.entiy.MenuVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {


    public static List<MenuVo> toTreeObject(List<MenuVo> MenuVos){
        List<MenuVo> MenuVoList = new ArrayList<>();
        for (MenuVo ps:MenuVos){
             if(ps.getPid() == null || ps.getPid() == 0){
                 MenuVoList.add(ps);
             }
        }
        treeChildren(MenuVoList,MenuVos);
        return  MenuVoList;
    }

    private  static  void  treeChildren(List<MenuVo> sysMenuVos,List<MenuVo> pers){
        for (MenuVo sys:sysMenuVos){
            List<MenuVo> children = new ArrayList<>();
            for(MenuVo  child:pers){
                if(sys.getId() != null && sys.getId() == child.getPid()){
                    children.add(child);
                }
            }
            sys.setChildren(children);
            treeChildren(children,pers);
        }


    }
}

