package com.multiauth.multiauthapplication.functions.accountmaster.service.impl;

import com.multiauth.multiauthapplication.common.email.EmailService;
import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.exemption.ExemptionErrorMessages;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.custom.AccountMasterCustomRepository;
import com.multiauth.multiauthapplication.functions.accountmaster.service.AccountMasterService;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;
import com.multiauth.multiauthapplication.functions.otp.service.OtpService;
import com.multiauth.multiauthapplication.util.CommonUtils;
import com.multiauth.multiauthapplication.util.dto.FindByPropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class AccountMasterServiceImpl implements AccountMasterService {

    @Autowired
    private AccountMasterCustomRepository accountMasterCustomRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private OtpService otpService;

    public AccountMasterDto createNewAccount(AccountMasterDto accountMasterDto) throws ExemptionError {

        FindByPropertyDto findByPropertyDto = new FindByPropertyDto();
        findByPropertyDto.setTableProperty("email");
        findByPropertyDto.setTableName("AccountMaster");
        findByPropertyDto.setSelectedTableProperty("id");
        findByPropertyDto.setTablePropertyValue(accountMasterDto.getEmail());

        Long accountId = commonUtils.findByProperties(findByPropertyDto, Long.class);

        if (!ObjectUtils.isEmpty(accountId) && !accountMasterDto.isForgotPass()) {
            throw new ExemptionError(ExemptionErrorMessages.ALREADY_HAVE_ACCOUNT);

        }

        if (ObjectUtils.isEmpty(accountMasterDto.getOtp())) {

            // GENERATE OTP
            String otpGenerated = emailService.sendOTPEmail(
                    accountMasterDto.getEmail(),
                    accountMasterDto.getFName() + " " + accountMasterDto.getLName());

            // CHECK IF OTP FOR SPECIFIC USER IS EXIST
            FindByPropertyDto findOtpPropertyDto = new FindByPropertyDto();
            findOtpPropertyDto.setTablePropertyValue(accountMasterDto.getEmail());
            findOtpPropertyDto.setSelectedTableProperty("id");
            findOtpPropertyDto.setTableProperty("userName");
            findOtpPropertyDto.setTableName("Otp");

            Long otpId = commonUtils.findByProperties(findOtpPropertyDto, Long.class);

            OtpDto otpDto = OtpDto.builder()
                    .username(accountMasterDto.getEmail())
                    .expiryDateTime(LocalDateTime.now().plusMinutes(5))
                    .otp(otpGenerated)
                    .build();

            if (ObjectUtils.isEmpty(otpId)) {
                otpService.createNewOtp(otpDto);

            } else {
                otpService.updateCurrentOtp(otpDto);

            }
        } else {
            accountMasterCustomRepository.createNewAccount(accountMasterDto);

        }

        return accountMasterDto;
    }
}
