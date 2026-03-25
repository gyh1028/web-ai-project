package com.ahu.aop;

import com.ahu.mapper.LogMapper;
import com.ahu.pojo.Log;
import com.ahu.util.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogMapper logMapper;

    //环绕通知
    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, com.ahu.annotation.Log log) throws Throwable{
        //记录开始时间
        long startTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //当前时间
        long endTime = System.currentTimeMillis();
        //耗时
        long costTime = endTime - startTime;

        //构建日志对象
        Log logInfo = new Log();
        logInfo.setOperateEmpId(getCurrentUserId());        //需要实现
        logInfo.setOperateTime(LocalDateTime.now());
        logInfo.setClassName(joinPoint.getTarget().getClass().getName());
        logInfo.setMethodName(joinPoint.getSignature().getName());
        logInfo.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        logInfo.setReturnValue(result.toString());
        logInfo.setCostTime(costTime);
        logInfo.setOperateEmpName(getCurrentUserName());     //需要实现

        //插入日志
        logMapper.insertLog(logInfo);
        return result;
    }

    private Integer getCurrentUserId(){
        return CurrentHolder.getCurrentId();
    }

    private String getCurrentUserName(){
        return CurrentHolder.getCurrentUsername();
    }
}
