package com.yudear.mooc.common.controller;


import com.yudear.mooc.common.exception.BizException;
import com.yudear.mooc.common.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一处理异常类
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 自定义处理异常
     * @return
     */
    @ExceptionHandler(BizException.class)
    public R  handlerException(BizException e){
        return  R.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public R handlerException(Throwable e){
        return R.error(500,"服务器错误",e.getMessage());
    }

}
