package com.multiauth.multiauthapplication.functions.forgotpassword.controller;


import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.forgotpassword.dto.ForgotPasswordDto;
import com.multiauth.multiauthapplication.functions.forgotpassword.service.ForgotPasswordService;
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
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("forgot-password")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) throws ExemptionError {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(forgotPasswordService.sendOtpRequest(forgotPasswordDto))
                .build();
    }
}
