package com.yudear.mooc.common.response;

import java.util.concurrent.ConcurrentHashMap;

//业务处理类
public class RetResponse extends ConcurrentHashMap<String ,Object> {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    public RetResponse(){
        this.put("code", RetCode.SUCCESS.code);
        this.put("msg",RetCode.SUCCESS.msg);
    }

    public static RetResponse success(){
        return  new RetResponse();
    }



    public static RetResponse success(Object data){
        return RetResponse.success().put("data",data);
    }

    public static RetResponse success(String msg, Object data){
        return RetResponse.success().put("msg",msg).put("data",data);
    }

    public static RetResponse error(){
        return RetResponse.success().put("code",RetCode.FAIL.code).put("msg",RetCode.FAIL.msg);
    }

    public static RetResponse error(String msg){
        return RetResponse.success().put("code",RetCode.FAIL.code).put("msg",msg);
    }

    public static RetResponse error(RetCode retCode){
        return RetResponse.success().put("code",retCode.code).put("msg",retCode.msg);
    }

    public static RetResponse error(int code ,String msg){
        return RetResponse.success().put("code",code).put("msg",msg);
    }



    @Override
    public RetResponse put(String key, Object data){
        super.put(key,data);
        return this;
    }

}
