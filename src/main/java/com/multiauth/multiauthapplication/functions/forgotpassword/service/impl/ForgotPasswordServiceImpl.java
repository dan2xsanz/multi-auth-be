package com.multiauth.multiauthapplication.functions.forgotpassword.service.impl;

import com.multiauth.multiauthapplication.common.email.EmailService;
import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.exemption.ExemptionErrorMessages;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.AccountMasterRepository;
import com.multiauth.multiauthapplication.functions.forgotpassword.dto.ForgotPasswordDto;
import com.multiauth.multiauthapplication.functions.forgotpassword.service.ForgotPasswordService;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;
import com.multiauth.multiauthapplication.functions.otp.service.OtpService;
import com.multiauth.multiauthapplication.model.AccountMaster;
import com.multiauth.multiauthapplication.util.CommonUtils;
import com.multiauth.multiauthapplication.util.dto.FindByPropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private AccountMasterRepository accountMasterRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private OtpService otpService;

    @Override
    public AccountMaster sendOtpRequest(ForgotPasswordDto forgotPasswordDto) throws ExemptionError {

        AccountMaster accountMaster = accountMasterRepository.findAccountByEmail(forgotPasswordDto.getEmail()).orElse(null);

        if (ObjectUtils.isEmpty(accountMaster)) {
            throw new ExemptionError(ExemptionErrorMessages.EMAIL_NOT_FOUND);
        }
        // GENERATE OTP
        String otpGenerated = emailService.sendOTPEmail(accountMaster.getEmail(), accountMaster.getFirstName() + " " + accountMaster.getLastName());

        // CHECK IF OTP FOR SPECIFIC USER IS EXIST
        FindByPropertyDto findOtpPropertyDto = new FindByPropertyDto();
        findOtpPropertyDto.setTablePropertyValue(accountMaster.getEmail());
        findOtpPropertyDto.setSelectedTableProperty("id");
        findOtpPropertyDto.setTableProperty("userName");
        findOtpPropertyDto.setTableName("Otp");

        Long otpId = commonUtils.findByProperties(findOtpPropertyDto, Long.class);

        OtpDto otpDto = OtpDto.builder()
                .username(accountMaster.getEmail())
                .expiryDateTime(LocalDateTime.now().plusMinutes(5))
                .otp(otpGenerated)
                .build();

        if (ObjectUtils.isEmpty(otpId)) {
            otpService.createNewOtp(otpDto);

        } else {
            otpService.updateCurrentOtp(otpDto);

        }

        return accountMaster;
    }
}
