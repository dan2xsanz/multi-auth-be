package com.multiauth.multiauthapplication.functions.forgotpassword.service;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.forgotpassword.dto.ForgotPasswordDto;
import com.multiauth.multiauthapplication.model.AccountMaster;

public interface ForgotPasswordService {

    AccountMaster sendOtpRequest(ForgotPasswordDto forgotPasswordDto) throws ExemptionError;
}
