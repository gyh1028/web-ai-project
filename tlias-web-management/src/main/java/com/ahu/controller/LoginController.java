package com.ahu.controller;

import com.ahu.pojo.Emp;
import com.ahu.pojo.Login;
import com.ahu.pojo.Result;
import com.ahu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){    //请求参数放在请求体时需加注解@RequestBody 路径参数则加注解@PathVariable
        log.info("登录参数：{}", emp);
        Login login = empService.login(emp);      //因为是在员工里面查找登录信息，所以可用EmpService接口
        if(login != null){
            return Result.success(login);
        }
        return Result.error("用户名或密码错误");
    }
}
