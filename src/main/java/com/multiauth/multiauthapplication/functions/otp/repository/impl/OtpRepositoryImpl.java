package com.multiauth.multiauthapplication.functions.otp.repository.impl;

import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.otp.dto.OtpDto;
import com.multiauth.multiauthapplication.functions.otp.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OtpRepositoryImpl implements OtpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Creates a new OTP (One-Time Password) record in the database.
     *
     * @param otpDto An object containing the OTP information, including expiry date and time, OTP value, and username.
     */
    public void createNewOtp(OtpDto otpDto) {

        String insertSelection =
                "INSERT INTO Otp " +
                        "(expiryDateTime, " +
                        "otp, " +
                        "username) " +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.update(insertSelection,
                otpDto.getExpiryDateTime(),
                otpDto.getOtp(),
                otpDto.getUsername());
    }

    /**
     * Updates the expiry date and time and OTP value for the current OTP record associated with the specified username in the database.
     *
     * @param otpDto An object containing the updated OTP information, including the new expiry date and time, OTP value, and username.
     */
    public void updateCurrentOtp(OtpDto otpDto) {
        String updateSelection =
                "UPDATE Otp " +
                        "SET expiryDateTime = ?, " +
                        "otp = ? " +
                        "WHERE username = ?";

        jdbcTemplate.update(updateSelection,
                otpDto.getExpiryDateTime(),
                otpDto.getOtp(),
                otpDto.getUsername());
    }


    @Override
    public Long validateUserNameOtp(AccountMasterDto accountMasterDto) {
        String mainQuery = "SELECT id FROM Otp " +
                "WHERE userName = ? " +
                "AND otp = ? " +
                "AND expiryDateTime > NOW() ";

        try {
            return jdbcTemplate.queryForObject(mainQuery, Long.class, accountMasterDto.getEmail(), accountMasterDto.getOtp());

        } catch (Exception e) {
            return null;

        }
    }
}
