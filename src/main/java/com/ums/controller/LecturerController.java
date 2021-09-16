package com.ums.controller;

import com.ums.model.request.GradeRequest;
import com.ums.model.request.StudentGroupRequest;
import com.ums.model.request.UpdateGradeRequest;
import com.ums.model.response.GradeResponse;
import com.ums.model.response.LecturerResponse;
import com.ums.model.response.StudentResponse;
import com.ums.model.response.SubjectResponse;
import com.ums.service.entity.GradeService;
import com.ums.service.entity.LecturerService;
import com.ums.service.entity.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final LecturerService lecturerService;

    public LecturerController(GradeService gradeService, StudentService studentService, LecturerService lecturerService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @PostMapping("/create_grade")
    @ResponseStatus(HttpStatus.CREATED)
    public GradeResponse createGrade(@RequestBody @Valid GradeRequest grade) {
        return gradeService.create(grade);
    }

    @PatchMapping("/update_grade")
    public GradeResponse updateGrade(long id, @RequestBody @Valid UpdateGradeRequest newGrade) {
        return gradeService.update(id, newGrade);
    }

    @GetMapping("/students_in_group")
    public Page<StudentResponse> getStudentsInGroup(@RequestBody @Valid StudentGroupRequest group, @Parameter(hidden = true) Pageable pageable) {
        return studentService.getStudentsInGroup(group, pageable);
    }

    @GetMapping("/{id}/my_subjects/")
    public Page<SubjectResponse> getSubjects(@PathVariable int id, @Parameter(hidden = true) Pageable pageable) {
        return lecturerService.getSubjects(id, pageable);
    }

    @GetMapping("/list")
    public Page<LecturerResponse> getAllLecturers(@Parameter(hidden = true) Pageable pageable) {
        return lecturerService.getLecturerList(pageable);
    }
}
