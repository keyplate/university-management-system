package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CourseRequest {

    @Positive
    @NotNull
    Integer lecturerId;

    @Positive
    @NotNull
    Integer subjectId;
}
