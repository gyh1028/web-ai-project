package com.ahu.mapper;

import com.ahu.pojo.Clazz;
import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    public List<Clazz> list(EmpQueryParam clazzQueryParam);

    //查找可选班主任
//    @Select("select * from emp where job = 1")
//    public List<Emp> findAll();

    //根据id删除班级
    @Delete("delete from clazz where id = #{id}")
    public void deleteById(Integer id);

    //添加班级
    @Insert("insert into clazz(id, name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{id},#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    public void save(Clazz clazz);

    //根据id查询班级
    @Select("select * from clazz where id = #{id}")
    public Clazz getInfo(Integer id);

    //修改班级信息
    @Insert("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, " +
            "master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    public void update(Clazz clazz);

    List<Clazz> findAll();
}
