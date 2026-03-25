package com.ahu.controller;

import com.ahu.pojo.*;
import com.ahu.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //班级列表查询（条件分页）
    @GetMapping
    public Result page(EmpQueryParam clazzQueryParam){
        log.info("查询班级列表：{}", clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

//    //新增班级班主任可选人员查询回显
//    @GetMapping("/list")
//    public Result list(){
//        log.info("查询可选班主任列表");
//        List<Emp> empList = clazzService.list();
//        return Result.success(empList);
//    }

    //删除班级
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
//        clazzService.deleteById(id);
//        return Result.success();
        clazzService.deleteById(id);
        return Result.success();
    }

    //添加班级
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级：{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    //根据id查询班级
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询班级：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    //修改班级
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    //查询所有班级 有必要吗？
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }
}
