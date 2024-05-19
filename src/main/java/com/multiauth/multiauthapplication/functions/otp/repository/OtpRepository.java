package com.multiauth.multiauthapplication.functions.otp.repository;

import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;

public interface OtpRepository {

    /**
     * Creates a new OTP (One-Time Password) record in the database.
     *
     * @param otpDto An object containing the OTP information, including expiry date and time, OTP value, and username.
     */
    void createNewOtp(OtpDto otpDto);

    /**
     * Updates the expiry date and time and OTP value for the current OTP record associated with the specified username in the database.
     *
     * @param otpDto An object containing the updated OTP information, including the new expiry date and time, OTP value, and username.
     */
    void updateCurrentOtp(OtpDto otpDto);

    Long validateUserNameOtp(AccountMasterDto accountMasterDto);

}
