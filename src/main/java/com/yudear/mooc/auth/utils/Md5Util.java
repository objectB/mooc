package com.yudear.mooc.auth.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Util {


    public static String md5(String password){
        //计算盐值
        Object salt = ByteSource.Util.bytes("salt");
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 2);
        return simpleHash.toHex();
    }



}
