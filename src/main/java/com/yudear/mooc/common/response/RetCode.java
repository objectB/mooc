package com.yudear.mooc.common.response;

/**
 * 定义相应码枚举
 */
public enum  RetCode {
    //成功
    SUCCESS(200 , "success"),
    //失败
    FAIL(400,"fail"),
    //未认证
    UNAUTHORIZED(4001,"认证失败"),

    UNUSERTOKEN(4003,"token不能为空"),

    //没权限
    UNAUTHORIZATI0N(4002,"没有权限"),
    //服务内部错误
    INTERNAL_SERVER_ERROR(5001,"服务器错误");



    public int code;
    public String msg;
    RetCode(int code,String msg){
        this.code = code;
        this.msg = msg;

    }
}
