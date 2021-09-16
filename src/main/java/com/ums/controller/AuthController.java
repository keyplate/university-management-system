package com.ums.controller;

import com.ums.model.request.SignInRequest;
import com.ums.model.response.JWTTokenResponse;
import com.ums.service.auth.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public JWTTokenResponse authorize(@RequestBody @Valid SignInRequest signInRequest) {
        return authService.authenticate(signInRequest);
    }
}
