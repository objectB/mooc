package com.yudear.mooc.common.model;

import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;

//业务处理类
public class R  extends ConcurrentHashMap<String ,Object> {


    public R(){
        this.put("code", HttpStatus.OK.value());
        this.put("msg","success !!!");
    }

    public static R ok(){
        return  new R();
    }

    public static R ok(String msg){
        return R.ok().put("msg",msg);
    }

    public static R ok(Object data){
        return R.ok().put("data",data);
    }

    public static R ok(String msg,Object data){
        return R.ok().put("msg",msg).put("data",data);
    }




    public static R error(int code,String error){
        return R.ok().put("error",error).put("code",code).put("msg","");
    }

    public static R error(int code,String error,String msg){
        return R.ok().put("error",error).put("code",code).put("msg",msg);
    }


    @Override
    public R put(String key,Object data){
        super.put(key,data);
        return this;
    }

}
