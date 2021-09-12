package com.ums.model.response;

import com.ums.model.entity.student.Student;
import lombok.Data;

@Data
public class SaveStudentResponse extends AccountResponse{
    private String groupName;

    public static SaveStudentResponse studentResponse(Student student) {
        SaveStudentResponse responseObject = new SaveStudentResponse();
        responseObject.id = student.getId();
        responseObject.email = student.getEmail();
        responseObject.firstName = student.getFirstName();
        responseObject.lastName = student.getLastName();
        responseObject.status = student.getStatus();
        responseObject.groupName = student.getGroup().getName();
        return responseObject;
    }
}
