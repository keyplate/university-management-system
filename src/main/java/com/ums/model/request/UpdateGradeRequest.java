package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateGradeRequest {

    @Positive
    @NotNull
    Integer value;
}
