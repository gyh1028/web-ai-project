package com.ahu.service;

import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import com.ahu.pojo.Login;
import com.ahu.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //分页查询
    //页码
    //每页记录数
    PageResult page(EmpQueryParam empQueryParam);

    //添加员工
    public void save(Emp emp);

    //批量删除员工
    public void deleteByIds(List<Integer> ids);

    //查询回显
    Emp getInfo(Integer id);

    //更新员工信息
    void update(Emp emp);

    //班主任接口
    List<Emp> list();

    //登录
    public Login login(Emp emp);
}
