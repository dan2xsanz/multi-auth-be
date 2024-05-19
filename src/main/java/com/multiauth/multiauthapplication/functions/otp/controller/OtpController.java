package com.multiauth.multiauthapplication.functions.otp.controller;


import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.otp.service.OtpService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("verify-user")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel verifyUser(@RequestBody AccountMasterDto accountMasterDto) throws ExemptionError {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(otpService.validateUserNameOtp(accountMasterDto))
                .build();
    }
}

