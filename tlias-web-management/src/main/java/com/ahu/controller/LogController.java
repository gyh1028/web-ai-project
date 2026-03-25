package com.ahu.controller;

import com.ahu.pojo.LogQueryParam;
import com.ahu.pojo.PageResult;
import com.ahu.pojo.Result;
import com.ahu.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    //日志列表查询
    @GetMapping("/page")
    public Result list(LogQueryParam logQueryParam){
        log.info("查询日志列表，参数：{}", logQueryParam);
        PageResult pageResult = logService.list(logQueryParam);
        return Result.success(pageResult);
    }
}
