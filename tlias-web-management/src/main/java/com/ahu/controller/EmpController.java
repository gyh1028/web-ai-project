package com.ahu.controller;

import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import com.ahu.pojo.PageResult;
import com.ahu.pojo.Result;
import com.ahu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //（条件）分页查询员工信息及所属部门信息
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
//        public Result page(@RequestParam(defaultValue = "1") Integer page,
//                @RequestParam(defaultValue = "10") Integer pageSize,
//                String name, Integer gender,
//                @DateTimeFormat(pattern = "yyyy-MM--dd")LocalDate begin,
//                @DateTimeFormat(pattern = "yyyy-MM--dd")LocalDate end){
        log.info("查询请求参数，{}", empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //添加员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("请求参数emp：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //批量删除员工
    @DeleteMapping
//    public Result selete(Integer[] ids){
//        log.info("批量删除部门：ids={}", Arrays.asList(ids));
//        return Result.success();
//    }     //通过一个数组来接收

    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门：ids={}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //更新员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){

        log.info("更新员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    //写一个查询所有员工信息的接口 递给前端便于其在班级班主任中显示可选员工
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}
