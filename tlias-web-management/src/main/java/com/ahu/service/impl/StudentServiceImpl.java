package com.ahu.service.impl;

import com.ahu.mapper.StudentMapper;
import com.ahu.pojo.PageResult;
import com.ahu.pojo.Student;
import com.ahu.pojo.StudentQueryParam;
import com.ahu.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageResult page(StudentQueryParam studentQueryParam){
        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());

        //执行查询
        List<Student> list = studentMapper.list(studentQueryParam);
        PageInfo<Student> p = new PageInfo<Student>(list);

        //封装结果
        return new PageResult(p.getTotal(),p.getList());
    }

    //批量删除学员
    @Override
    public void deleteByIds(Integer[] ids){
        studentMapper.deleteByIds(ids);
    }

    //添加学员
    @Override
    public void save(Student student){

        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    //查询回显
    @Override
    public Student getInfo(Integer id){
        return studentMapper.getInfo(id);
    }

    //修改学员
    @Override
    public void update(Student student){
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    //违纪处理
    @Override
    public void violation(Integer id, Integer score){
        //添加违纪
        if(score > 0) {
            studentMapper.violation(id, score);
        }
    }
}
