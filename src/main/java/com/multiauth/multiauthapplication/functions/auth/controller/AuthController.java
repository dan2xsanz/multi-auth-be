package com.multiauth.multiauthapplication.functions.auth.controller;

import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.service.AuthService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel logIn(@RequestBody LoginRequestDto loginRequestDto) {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(authService.loginRequest(loginRequestDto))
                .build();
    }
}
