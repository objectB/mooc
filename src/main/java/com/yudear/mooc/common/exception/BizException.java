package com.yudear.mooc.common.exception;

public class BizException extends RuntimeException {


    private int code;


    public BizException(String message) {
        super(message);
    }


    public BizException(int code ,String msg){
      super(msg);
      this.code = code;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
