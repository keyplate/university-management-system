package com.ums.controller;

import com.ums.model.entity.grade.Grade;
import com.ums.model.request.SaveStudentRequest;
import com.ums.model.response.GradeResponse;
import com.ums.model.response.SaveStudentResponse;
import com.ums.service.GradeService;
import com.ums.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;
    private GradeService gradeService;

    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/{id}/my_grades")
    @PageableAsQueryParam
    public Page<GradeResponse> getGrades(@PathVariable int id, @Parameter(hidden = true) Pageable pageable) {
        return gradeService.getStudentGrades(id, pageable);
    }

    @PostMapping
    public SaveStudentResponse createStudent(@RequestBody @Valid SaveStudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }
}
