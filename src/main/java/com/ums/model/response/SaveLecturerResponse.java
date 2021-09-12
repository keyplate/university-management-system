package com.ums.model.response;

import com.ums.model.entity.lecturer.Lecturer;

public class SaveLecturerResponse extends AccountResponse{

    public static SaveLecturerResponse lecturerResponse(Lecturer lecturer) {
        SaveLecturerResponse responseObject = new SaveLecturerResponse();
        responseObject.id = lecturer.getId();
        responseObject.email = lecturer.getEmail();
        responseObject.firstName = lecturer.getFirstName();
        responseObject.lastName = lecturer.getLastName();
        responseObject.status = lecturer.getStatus();
        return responseObject;
    }
}
