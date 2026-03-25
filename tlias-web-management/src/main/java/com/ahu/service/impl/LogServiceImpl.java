package com.ahu.service.impl;

import com.ahu.mapper.LogMapper;
import com.ahu.pojo.Log;
import com.ahu.pojo.LogQueryParam;
import com.ahu.pojo.PageResult;
import com.ahu.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult list(LogQueryParam logQueryParam){
        //设置分页参数
        PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
        List<Log> logList = logMapper.list(logQueryParam);
        PageInfo<Log> p = new PageInfo<Log>(logList);

        return new PageResult(p.getTotal(), p.getList());
    }

    @Override
    public void insertLog(Log log){
        logMapper.insertLog(log);
    }
}
