package com.ums.controller;

import com.ums.model.entity.student.SaveStudentRequest;
import com.ums.model.entity.student.StudentResponse;
import com.ums.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public StudentResponse register(@RequestBody @Valid SaveStudentRequest student) {
        return studentService.create(student);
    }
}
