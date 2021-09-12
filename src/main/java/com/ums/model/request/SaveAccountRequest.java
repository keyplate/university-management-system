package com.ums.model.request;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveAccountRequest {

    @Email(message = "Email should follow this pattern abc@abc.abc")
    @NotNull
    String email;

    @NotBlank(message = "Password can't be empty")
    String password;

    @NotBlank(message = "First name can't be empty")
    String firstName;

    @NotBlank(message = "Last name can't be empty")
    String lastName;


}
