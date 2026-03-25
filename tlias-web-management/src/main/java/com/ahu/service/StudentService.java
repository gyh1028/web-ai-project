package com.ahu.service;

import com.ahu.pojo.PageResult;
import com.ahu.pojo.Student;
import com.ahu.pojo.StudentQueryParam;

import java.util.Arrays;

public interface StudentService {

    //条件查询学生列表
    PageResult page(StudentQueryParam studentQueryParam);

    //批量删除学员
    void deleteByIds(Integer[] ids);

    //添加学员
    void save(Student student);

    //查询回显
    Student getInfo(Integer id);

    //修改学员
    void update(Student student);

    void violation(Integer id, Integer score);
}
