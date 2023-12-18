package com.example.springboottest.controller;

import com.example.springboottest.domain.Res.StudentRes;
import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.Student;
import com.example.springboottest.domain.req.StudentReq;
import com.example.springboottest.domain.req.StudentUpdateReq;
import com.example.springboottest.servcice.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 学生信息控制层
 * @author lwy
 */
@RestController
@RequestMapping("/student")
@Validated
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/save")
    public ResultInfo<Boolean> insertStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @PostMapping("/update")
    public ResultInfo<Boolean> updateStudent(@RequestBody StudentUpdateReq student) {
        return studentService.updateStudent(student);
    }
    @PostMapping("/query_list")
    public ResultInfo<List<StudentRes>> queryStudentList(@RequestBody StudentReq req) {
        return studentService.getStudentList(req);
    }
    @PostMapping("query_by_id")
    public ResultInfo<StudentRes> getStudentById(@RequestParam(value = "id",required = true) @NotBlank Long id) {
        return studentService.getStudentById(id);
    }

}
