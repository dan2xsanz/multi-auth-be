package com.multiauth.multiauthapplication.functions.otp.service;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;

public interface OtpService {

    void createNewOtp(OtpDto otpDto);

    void updateCurrentOtp(OtpDto otpDto);

    Object validateUserNameOtp(AccountMasterDto accountMasterDto) throws ExemptionError;
}
