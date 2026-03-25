package com.ahu.service;

import com.ahu.pojo.Clazz;
import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import com.ahu.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    //查询班级列表
    PageResult page(EmpQueryParam clazzQueryParam);

    //查询可选班主任列表
    //List<Emp> list();

    //根据id删除班级
    void deleteById(Integer id);

    //添加班级
    void save(Clazz clazz);

    //根据id查询班级信息
    Clazz getById(Integer id);

    //修改班级
    void update(Clazz clazz);

    List<Clazz> list();
}
