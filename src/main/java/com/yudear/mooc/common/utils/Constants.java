package com.yudear.mooc.common.utils;

import org.apache.shiro.util.CollectionUtils;

import java.util.List;

public interface Constants {


    List<String> NO_NEED_FILTER = CollectionUtils.asList("/login","/index","/","/movies");

}
