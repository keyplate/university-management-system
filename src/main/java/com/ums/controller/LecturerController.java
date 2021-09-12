package com.ums.controller;

import com.ums.model.request.GradeRequest;
import com.ums.model.request.UpdateGradeRequest;
import com.ums.model.response.GradeResponse;
import com.ums.service.GradeService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {

    private GradeService gradeService;

    public LecturerController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/create_grade")
    public GradeResponse createGrade(@RequestBody @Valid GradeRequest grade) {
        return gradeService.create(grade);
    }

    @PatchMapping("update_grade")
    public GradeResponse updateGrade(long id, @RequestBody @Valid UpdateGradeRequest newGrade) {
        return gradeService.update(id, newGrade);
    }
}
