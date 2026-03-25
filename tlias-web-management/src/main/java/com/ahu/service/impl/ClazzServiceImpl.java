package com.ahu.service.impl;

import com.ahu.mapper.ClazzMapper;
import com.ahu.pojo.Clazz;
import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import com.ahu.pojo.PageResult;
import com.ahu.service.ClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(EmpQueryParam clazzQueryParam){

        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());

        //执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        PageInfo<Clazz> p = new PageInfo<Clazz>(clazzList);

        // 计算每个班级的状态 - 使用数据库中每个班级的 beginDate 和 endDate
        for (Clazz clazz : p.getList()) {
            clazz.setStatus(calculateStatus(clazz.getBeginDate(), clazz.getEndDate(), LocalDate.now()));
        }

        //封装结果
        return new PageResult(p.getTotal(),p.getList());
    }

    //查询可选班主任
//    @Override
//    public List<Emp> list(){
//        return clazzMapper.findAll();
//    }

    //根据id删除班级
    @Override
    public void deleteById(Integer id){
        clazzMapper.deleteById(id);
    }

    //添加班级
    @Override
    public void save(Clazz clazz){
        //补全基本信息
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.save(clazz);
    }

    //根据id查询班级信息
    @Override
    public Clazz getById(Integer id){
        return clazzMapper.getInfo(id);
    }

    //修改班级
    @Override
    public void update(Clazz clazz){
        //补全基本信息
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.update(clazz);
    }

    //查询所有班级
    @Override
    public List<Clazz> list(){
        return clazzMapper.findAll();
    }

    //计算班级状态
    private String calculateStatus(LocalDate beginDate, LocalDate endDate, LocalDate now) {
        if (now == null) {
            now = LocalDate.now();
        }

        // 如果数据库中没有设置时间，返回未知
        if (beginDate == null && endDate == null) {
            return "未知";
        }

        // 只比较 beginDate：当前时间 < 开课时间 → 未开班
        if (beginDate != null && now.isBefore(beginDate)) {
            return "未开班";
        }
        // 只比较 endDate：当前时间 > 结课时间 → 已结课
        else if (endDate != null && now.isAfter(endDate)) {
            return "已结课";
        }
        // 其他情况：开课时间 ≤ 当前时间 ≤ 结课时间 → 在读
        else {
            return "在读";
        }
    }
}
