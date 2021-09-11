package com.ums.model.entity.student;

import com.ums.model.entity.account.request.SaveAccountRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaveStudentRequest extends SaveAccountRequest {

    @NotBlank(message = "Student requires group")
    String group;
}
