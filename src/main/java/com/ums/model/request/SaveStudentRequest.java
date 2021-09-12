package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SaveStudentRequest extends SaveAccountRequest {

    @NotBlank(message = "Student requires group")
    String group;
}
