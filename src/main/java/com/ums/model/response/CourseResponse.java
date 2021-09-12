package com.ums.model.response;

import com.ums.model.entity.course.Course;
import lombok.Data;

@Data
public class CourseResponse {

    private Long id;
    private Integer subjectId;
    private Integer lecturerId;

    public static CourseResponse courseResponse(Course course) {
        CourseResponse responseObject = new CourseResponse();
        responseObject.setId(course.getId());
        responseObject.setLecturerId(course.getLecturer().getId());
        responseObject.setSubjectId(course.getSubject().getId());
        return responseObject;
    }
}
