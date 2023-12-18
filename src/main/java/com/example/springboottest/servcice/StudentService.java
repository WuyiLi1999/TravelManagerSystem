package com.example.springboottest.servcice;

import com.example.springboottest.domain.Res.StudentRes;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.Student;
import com.example.springboottest.domain.req.StudentReq;
import com.example.springboottest.domain.req.StudentUpdateReq;

import java.util.List;

/**
 *学生业务层Service
 * @author Smile
 */
public interface StudentService {
    /**
     * 保存学生信息
     * @param student
     * @return
     */
    ResultInfo<Boolean> saveStudent(Student student);

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    ResultInfo<Boolean> updateStudent(StudentUpdateReq student);

    /**
     * 查询学生信息列表
     * @param req
     * @return
     */
    ResultInfo<List<StudentRes>> getStudentList(StudentReq req);

    /**
     * 根据学生id获取学生信息
     * @param id
     * @return
     */
    ResultInfo<StudentRes> getStudentById(Long id);

}
