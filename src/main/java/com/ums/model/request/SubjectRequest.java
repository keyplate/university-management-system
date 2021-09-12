package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SubjectRequest {

    @NotBlank
    String title;
}
