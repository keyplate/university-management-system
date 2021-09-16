package com.ums.model.response;

import com.ums.model.entity.lecturer.Lecturer;

public class LecturerResponse extends AccountResponse{

    public static LecturerResponse lecturerResponse(Lecturer lecturer) {
        LecturerResponse responseObject = new LecturerResponse();
        responseObject.id = lecturer.getId();
        responseObject.email = lecturer.getEmail();
        responseObject.firstName = lecturer.getFirstName();
        responseObject.lastName = lecturer.getLastName();
        responseObject.status = lecturer.getStatus();
        return responseObject;
    }
}
