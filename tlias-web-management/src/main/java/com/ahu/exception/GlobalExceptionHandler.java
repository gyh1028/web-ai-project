package com.ahu.exception;

import com.ahu.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler
    public Result ex(Exception e){
        e.printStackTrace();
        if(e.getMessage().contains("a foreign key")){
            return Result.error("删除失败，存在外键冲突");
        }
        if(e.getMessage().contains("student.no")){
            if(e.getMessage().contains("null")){
                return Result.error("学号不能为空");
            }
            return Result.error("学号已被占用");
        }
        if(e.getMessage().contains("username")){
            if(e.getMessage().contains("null")){
                return Result.error("用户名不能为空");
            }
            return Result.error("用户名已被占用");
        }
        if(e.getMessage().contains("name")){
            if(e.getMessage().contains("null")){
                return Result.error("姓名不能为空");
            }
            return Result.error("姓名已被占用");
        }
        if(e.getMessage().contains("phone")){
            if(e.getMessage().contains("null")){
                return Result.error("手机号不能为空");
            }
            return Result.error("手机号已被占用");
        }
        if(e.getMessage().contains("id_card")){
            if(e.getMessage().contains("null")){
                return Result.error("身份证号不能为空");
            }
            return Result.error("身份证号已被占用");
        }
        return Result.error("系统错误，请联系管理员");
    }
}
