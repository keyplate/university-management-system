package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class StudentGroupRequest {

    @NotBlank
    String name;
}
