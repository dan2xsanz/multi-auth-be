package com.multiauth.multiauthapplication.functions.auth.controller;

import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.service.AuthService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel logIn(@RequestBody LoginRequestDto loginRequestDto) throws IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(authService.loginRequest(loginRequestDto))
                .build();
    }
}
