package com.ahu.service.impl;

import com.ahu.mapper.EmpMapper;
import com.ahu.mapper.StudentMapper;
import com.ahu.pojo.ClazzOption;
import com.ahu.pojo.JobOption;
import com.ahu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData(){
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map> getEmpGenderData(){
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData(){
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData(){
        List<Map<String,Object>> list = studentMapper.countStudentCountData();
        List<Object> degreeList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new ClazzOption(degreeList,dataList);
    }
}
