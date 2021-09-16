package com.ums.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenResponse {

    private String email;

    private String accessToken;
}
