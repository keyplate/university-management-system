package com.ums.model.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class SignInRequest {

    @Email
    String email;

    String password;
}
