package com.ums.model.response;

import lombok.Data;
import com.ums.model.entity.group.StudentGroup;

@Data
public class StudentGroupResponse {

    private Integer id;
    private String name;

    public static StudentGroupResponse groupResponse(StudentGroup group) {
        var responseObject = new StudentGroupResponse();
        responseObject.setId(group.getId());
        responseObject.setName(group.getName());
        return responseObject;
    }
}
