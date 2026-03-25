package com.ahu.mapper;

import com.ahu.pojo.Log;
import com.ahu.pojo.LogQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {

    //日志列表查询
    List<Log> list(LogQueryParam logQueryParam);


    void insertLog(Log log);
}
