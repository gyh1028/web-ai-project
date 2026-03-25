package com.ahu.service;

import com.ahu.pojo.Log;
import com.ahu.pojo.LogQueryParam;
import com.ahu.pojo.PageResult;


public interface LogService {

    public PageResult list(LogQueryParam logQueryParam);

    public void insertLog(Log log);
}
