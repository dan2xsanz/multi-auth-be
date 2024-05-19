package com.multiauth.multiauthapplication.functions.otp.service.impl;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.exemption.ExemptionErrorMessages;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;
import com.multiauth.multiauthapplication.functions.otp.repository.OtpRepository;
import com.multiauth.multiauthapplication.functions.otp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    /**
     * Creates a new OTP (One-Time Password) record in the database.
     *
     * @param otpDto An object containing the OTP information, including expiry date and time, OTP value, and username.
     */
    @Override
    public void createNewOtp(OtpDto otpDto) {
        otpRepository.createNewOtp(otpDto);

    }

    /**
     * Updates the expiry date and time and OTP value for the current OTP record associated with the specified username in the database.
     *
     * @param otpDto An object containing the updated OTP information, including the new expiry date and time, OTP value, and username.
     */
    @Override
    public void updateCurrentOtp(OtpDto otpDto) {
        otpRepository.updateCurrentOtp(otpDto);

    }

    @Override
    public Object validateUserNameOtp(AccountMasterDto accountMasterDto) throws ExemptionError {

        Long otpId = otpRepository.validateUserNameOtp(accountMasterDto);

        if (ObjectUtils.isEmpty(otpId)) {
            throw new ExemptionError(ExemptionErrorMessages.OTP_INVALID);

        }

        return null;
    }
}
