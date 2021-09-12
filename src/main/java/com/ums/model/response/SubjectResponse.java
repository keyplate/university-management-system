package com.ums.model.response;

import com.ums.model.entity.group.StudentGroup;
import com.ums.model.entity.subject.Subject;
import lombok.Data;

@Data
public class SubjectResponse {
    private Integer id;
    private String title;

    public static SubjectResponse subjectResponse(Subject subject) {
        var responseObject = new SubjectResponse();
        responseObject.setId(subject.getId());
        responseObject.setTitle(subject.getTitle());
        return responseObject;
    }
}
