package com.ahu.controller;

import com.ahu.annotation.Log;
import com.ahu.pojo.Dept;
import com.ahu.pojo.Result;
import com.ahu.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j      //该注解相当于在类中定义了日志记录器，即private static Logger log = LoggerFactory. getLogger(Xxx. class);
@RequestMapping("/depts")       //提取请求路径的相同部分
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询部门列表
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    @Log
    public Result list(){
        log.info("查询部门列表");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    //删除部门
    @DeleteMapping
    @Log
    //public Result delete(@RequestParam("id") Integer deptId){   //其实可以直接去掉@RequestParam
    public Result delete(Integer id){
        //System.out.println("根据id删除部门："+id);     //但是这里的deptId必须要和上面传过来的参数命名一致，即id
        log.info("根据id删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    @Log
    public Result save(@RequestBody Dept dept){
        //System.out.println("新增部门，dept="+ dept);
        log.info("新增部门，{}",dept);
        deptService.save(dept);
        return Result.success();
    }

    //根据ID查询部门
    @GetMapping("/{id}")
    @Log
    public Result getById(@PathVariable("id") Integer id){
        //System.out.println("根据ID查询，id=" + id);
        log.info("根据ID查询，{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    @Log
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门，dept="+ dept);
        log.info("修改部门，{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
