package com.ahu.mapper;

import com.ahu.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有部门
    @Select("select * from dept")
    public List<Dept> findAll();

    //删除部门
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);

    //根据id查询部门
    @Select("select id,name,dept.create_time,dept.update_time from dept where id = #{id}")
    public Dept getById(Integer id);

    //修改部门
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);
}
