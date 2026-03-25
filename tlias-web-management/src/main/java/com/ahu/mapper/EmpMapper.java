package com.ahu.mapper;

import com.ahu.pojo.Emp;
import com.ahu.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //查询总记录数
    //@Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    //public Long count();

    //查询所有员工及其对应部门名称
    //@Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id limit #{start},#{pageSize}")
    //public List<Emp> list(Integer start, Integer pageSize);
//    @Select("Select e.*,d.name deptName from emp as e left join dept as d on e.dept_id = d.id " +
//            "where e.name like concat('%',#{name},'%') " +
//            "and e.gender = #{gender} " +
//            "and e.entry_date between #{begin} and #{end}")     //将sql语句写在这里显得代码过于臃肿，可放到映射文件中EmpMapper.xml中
    public List<Emp> list(EmpQueryParam empQueryParam);

    //新增员工数据
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //批量删除员工信息
    void deleteByIds(List<Integer> ids);

    //查询回显
    Emp getById(Integer id);

    //更新员工基本信息
    void updateById(Emp emp);

    //统计各个职位的员工人数
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //统计员工性别信息
    @MapKey("name")     //这个注解貌似不生效 因为mybatis只有在返回值为Map类型的接口方法前才会使用注解中的key-value映射
    List<Map> countEmpGenderData();

    List<Emp> findAll();

    //根据用户名（唯一）查询员工信息
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndName(Emp emp);
}
