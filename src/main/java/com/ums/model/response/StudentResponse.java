package com.ums.model.response;

import com.ums.model.entity.student.Student;
import lombok.Data;

@Data
public class StudentResponse extends AccountResponse{
    private String groupName;

    public static StudentResponse studentResponse(Student student) {
        StudentResponse responseObject = new StudentResponse();
        responseObject.id = student.getId();
        responseObject.email = student.getEmail();
        responseObject.firstName = student.getFirstName();
        responseObject.lastName = student.getLastName();
        responseObject.status = student.getStatus();
        responseObject.groupName = student.getGroup().getName();
        return responseObject;
    }
}
