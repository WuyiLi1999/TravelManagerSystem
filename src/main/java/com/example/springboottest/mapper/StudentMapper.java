package com.example.springboottest.mapper;

import com.example.springboottest.domain.Res.StudentRes;
import com.example.springboottest.domain.Student;
import com.example.springboottest.domain.req.StudentReq;
import com.example.springboottest.domain.req.StudentUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *学生信息数据层
 * @author lwy
 */
@Mapper
public interface StudentMapper {

    /**
     * 保存学生信息
     * @param student
     * @return
     */
    int saveStudent(@Param("student") Student student);

    /**
     * 更新学生信息
     * @param req
     * @return
     */
   int updateStudent(@Param("req") StudentUpdateReq req);

    /**
     * 查询学生信息列表
     * @param req
     * @return
     */
    List<StudentRes> getStudentList(@Param("req") StudentReq req);

    /**
     * 根据学生id获取学生信息
     * @param id
     * @return
     */
    StudentRes getStudentById(@Param("id") Long id);

    StudentRes getStudentByName(@Param("name") String name);

}
