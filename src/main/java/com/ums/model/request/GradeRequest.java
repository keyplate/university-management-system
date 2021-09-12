package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class GradeRequest {

    @Positive
    @NotNull
    Integer StudentId;

    @Positive
    @NotNull
    Long CourseId;

    @PositiveOrZero
    @NotNull
    Integer value;
}
