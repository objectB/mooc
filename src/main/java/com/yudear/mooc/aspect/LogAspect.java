package com.yudear.mooc.aspect;

import com.yudear.mooc.aspect.annotation.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.yudear.mooc.aspect.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveLog(JoinPoint joinPoint){

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog != null){

        }
    }
}
