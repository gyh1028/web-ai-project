package com.ahu.mapper;


import com.ahu.pojo.Student;
import com.ahu.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //条件分页查询学生列表
    List<Student> list(StudentQueryParam studentQueryParam);

    //批量删除学员
    void deleteByIds(Integer[] ids);

    //添加学员
    void insert(Student student);

    //查询回显
    Student getInfo(Integer id);

    //修改学员
    void update(Student student);

    //违纪处理
    void violation(Integer id, Integer score);

    //统计各个学历的学员人数
    @MapKey("name")     //这个注解貌似不生效 因为mybatis只有在返回值为Map类型的接口方法前才会使用注解中的key-value映射
    List<Map> countStudentDegreeData();

    //统计各个班级的学员人数
    @MapKey("clazz")
    List<Map<String,Object>> countStudentCountData();
}
