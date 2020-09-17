package com.yudear.mooc.common.utils;

import com.yudear.mooc.entiy.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {


    public static List<Permission> toTreeObject(List<Permission> list){
        Map<Integer, Permission> mapTmp = new HashMap<>();
        for (Permission current : list) {
            mapTmp.put(current.getId(), current);
        }
        List<Permission> finalList = new ArrayList<>();
        mapTmp.forEach((k, v) -> {
            if(v.getPid() == null || v.getPid().equals(0)) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });

        return  finalList;
    }
}

