package com.yudear.mooc.common.utils;

import com.yudear.mooc.entiy.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {


    public static List<Permission> toTreeObject(List<Permission> permissions){
        List<Permission> permissionList = new ArrayList<>();
        for (Permission ps:permissions){
             if(ps.getPid() == null || ps.getPid() == 0){
                 permissionList.add(ps);
             }
        }
        treeChildren(permissionList,permissions);
        return  permissionList;
    }

    private  static  void  treeChildren(List<Permission> sysPermissions,List<Permission> pers){
        for (Permission sys:sysPermissions){
            List<Permission> children = new ArrayList<>();
            for(Permission  child:pers){
                if(sys.getId() != null && sys.getId() == child.getPid()){
                    children.add(child);
                }
            }
            sys.setChildren(children);
            treeChildren(children,pers);
        }


    }
}

