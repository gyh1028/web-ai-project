package com.ahu.service.impl;

import com.ahu.mapper.EmpExprMapper;
import com.ahu.mapper.EmpMapper;
import com.ahu.pojo.*;
import com.ahu.service.EmpService;
import com.ahu.util.JwtUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;


    @Override
    public PageResult page(EmpQueryParam empQueryParam){
//        //获取总记录数
//        Long total = empMapper.count();
//
//        //获取结果列表
//        Integer start = (pageNum-1)*pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);
//
//        //封装结果
//        return new PageResult(total, empList);
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        //执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //Page<Emp> p = (Page<Emp>) empList;
        PageInfo<Emp> p = new PageInfo<Emp>(empList);

        //封装结果
        return new PageResult(p.getTotal(),p.getList());
    }

    @Transactional(rollbackFor = Exception.class)       //任何类型异常都可回滚
    @Override
    public void save(Emp emp){

            //补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //保存员工基本信息
            empMapper.insert(emp);

            //int i = 1/0;
            //模拟：异常发生
//        if(true){
//            throw new Exception("出现异常啦~");
//        }     //这里貌似不让模拟抛出异常 编译执行不了

            //保存员工工作经历信息 - 批量保存
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids){

            //根据员工id批量删除员工工作经历信息  这里出现问题：emp和expr属主从表，expr存在外键fk_emp_id关联emp，
            //所以数据库不允许先删除emp的员工信息
            empExprMapper.deleteByEmpIds(ids);

            //根据id批量删除员工基本信息
            empMapper.deleteByIds(ids);

    }

    @Transactional
    @Override
    public Emp getInfo(Integer id){
        return empMapper.getById(id);
    }

    @Transactional
    @Override
    public void update(Emp emp){

        //根据id更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据员工id删除员工工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //新增员工工作经历信息
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }

    }


    //查询所有员工信息（可选班主任）
    @Override
    public List<Emp> list(){
        return empMapper.findAll();
    }

    //登录
    @Override
    public Login login(Emp emp){
        Emp empLogin = empMapper.getUsernameAndName(emp);
        if(empLogin != null){
            //生成令牌token
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());
            dataMap.put("name", empLogin.getName());

            String token = JwtUtils.generateJwt(dataMap);
            return new Login(empLogin.getId(), empLogin.getUsername(), empLogin.getPassword(), token);
        }
        return null;
    }
}
