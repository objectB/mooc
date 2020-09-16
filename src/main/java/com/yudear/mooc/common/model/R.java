package com.yudear.mooc.common.model;

import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;

//业务处理类
public class R  extends ConcurrentHashMap<String ,Object> {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    public R(){
        this.put("code", SUCCESS_CODE);
        this.put("msg","success !!!");
    }

    public static R success(){
        return  new R();
    }

    public static R success(String msg){
        return R.success().put("msg",msg);
    }

    public static R success(Object data){
        return R.success().put("data",data);
    }

    public static R success(String msg,Object data){
        return R.success().put("msg",msg).put("data",data);
    }


    public static R error(String msg){
        return R.success().put("code",ERROR_CODE).put("msg",msg);
    }

    public static R error(int code,String msg){
        return R.success().put("code",code).put("msg",msg);
    }


    @Override
    public R put(String key,Object data){
        super.put(key,data);
        return this;
    }

}
