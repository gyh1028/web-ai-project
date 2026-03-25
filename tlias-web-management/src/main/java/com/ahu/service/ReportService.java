package com.ahu.service;

import com.ahu.pojo.ClazzOption;
import com.ahu.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    //统计各个职位的员工人数
    JobOption getEmpJobData();

    //统计员工性别性别信息
    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
