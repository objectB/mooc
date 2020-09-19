package com.yudear.mooc.common.controller;


import com.yudear.mooc.common.exception.BizException;
import com.yudear.mooc.common.response.RetResponse;
import com.yudear.mooc.common.response.RetCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotWritableException;
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
     *
     * @return
     */
    @ExceptionHandler(BizException.class)
    public RetResponse handlerException(BizException e) {
        return RetResponse.error( e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public RetResponse handlerException(Exception  e) {
        return RetResponse.error("权限不足");
    }


    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public RetResponse server500(RuntimeException ex) {
        System.err.println("RuntimeException:");
        return RetResponse.error(RetCode.INTERNAL_SERVER_ERROR);


    }
}
