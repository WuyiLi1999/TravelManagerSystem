package com.example.springboottest.servcice.impl;

import com.example.springboottest.domain.Res.StudentRes;
import com.example.springboottest.domain.Result;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.Student;
import com.example.springboottest.domain.req.StudentReq;
import com.example.springboottest.domain.req.StudentUpdateReq;
import com.example.springboottest.mapper.StudentMapper;
import com.example.springboottest.servcice.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResultInfo<Boolean> saveStudent(Student student) {
        StudentRes res=studentMapper.getStudentByName(student.getName());
        if (res!=null){
            return Result.OK(0,null,"该学生信息已存在","该学生信息已存在，请重新录入");
        }
        int count=studentMapper.saveStudent(student);
        return Result.OK(count,true,count>0?"添加成功":"添加失败",count>0?"添加成功":"添加失败");
    }

    @Override
    public ResultInfo<Boolean> updateStudent(StudentUpdateReq student) {
        StudentRes res=studentMapper.getStudentById(student.getId());
        if(res==null){
            return Result.OK(0,null,"该学生信息不存在，请重新更新","该学生信息不存在，请重新更新");
        }
        int count=studentMapper.updateStudent(student);
        return Result.OK(count,true,count>0?"更新成功":"更新失败",count>0?"更新成功":"更新失败");
    }

    @Override
    public ResultInfo<List<StudentRes>> getStudentList(StudentReq req) {
        return Result.OK(1,studentMapper.getStudentList(req),"","");
    }

    @Override
    public ResultInfo<StudentRes> getStudentById(Long id) {
        return Result.OK(1,studentMapper.getStudentById(id),"","");
    }
}
