package com.ahu.service.impl;

import com.ahu.annotation.Log;
import com.ahu.mapper.DeptMapper;
import com.ahu.pojo.Dept;
import com.ahu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //查询所有部门信息
    @Override
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }

    //删除部门
    @Override
    public void deleteById(Integer id){
        deptMapper.deleteById(id);
    }

    //新增部门
    @Override
    public void save(Dept dept){
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    //根据ID查询部门
    @Override
    public Dept getById(Integer id){
        return deptMapper.getById(id);
    }

    //修改部门
    @Override
    public void update(Dept dept){
        //补全基础属性 因为是修改 所以补上修改时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
