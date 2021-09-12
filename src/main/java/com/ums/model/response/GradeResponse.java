package com.ums.model.response;

import com.ums.model.entity.grade.Grade;
import lombok.Data;

@Data
public class GradeResponse {

    private Long id;
    private Long courseId;
    private Integer studentId;
    private Integer value;

    public static GradeResponse gradeResponse(Grade grade) {
        GradeResponse responseObject = new GradeResponse();
        responseObject.setId(grade.getId());
        responseObject.setCourseId(grade.getCourse().getId());
        responseObject.setStudentId(grade.getStudent().getId());
        responseObject.setValue(grade.getValue());
        return responseObject;
    }
}
