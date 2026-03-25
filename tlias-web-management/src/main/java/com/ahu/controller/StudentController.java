package com.ahu.controller;

import com.ahu.pojo.PageResult;
import com.ahu.pojo.Result;
import com.ahu.pojo.Student;
import com.ahu.pojo.StudentQueryParam;
import com.ahu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;


@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //查询学员列表（条件查询）
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam){
        log.info("查询学员列表，参数：{}", studentQueryParam);
        PageResult pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //删除学员 批量删除
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Integer[] ids){
        log.info("批量删除学员，id：{}", Arrays.toString(ids));
        studentService.deleteByIds(ids);
        return Result.success();
    }

    //添加学员
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员，参数：{}", student);
        studentService.save(student);
        return Result.success();
    }

    //根据id查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id:{}查询学员的详细信息",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    //修改学员
    @PutMapping
    public Result update(@RequestBody Student student){

        log.info("修改学员，参数：{}", student);
        studentService.update(student);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("违纪处理，学号为{},violationScore={}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
