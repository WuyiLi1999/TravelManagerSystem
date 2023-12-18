package com.example.springboottest;

import com.example.springboottest.domain.ResultInfo;
import com.example.springboottest.domain.Student;
import com.example.springboottest.servcice.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private StudentService studentService;
    @Test
    public void testSave(){
        Student student=new Student();
        student.setName("test");
        student.setAge(19);
        student.setSex(1);
        ResultInfo<Boolean> resultInfo= studentService.saveStudent(student);
        System.out.println(resultInfo.getCode());
    }
}
