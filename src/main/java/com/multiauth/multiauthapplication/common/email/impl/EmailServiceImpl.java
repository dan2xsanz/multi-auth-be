package com.multiauth.multiauthapplication.common.email.impl;

import com.multiauth.multiauthapplication.common.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private static final String NUMBERS = "0123456789";

    private static final Random RANDOM = new Random();

    @Override
    public String sendOTPEmail(String email, String name) {

        SimpleMailMessage message = new SimpleMailMessage();

        String otpGenerated = generateOTP(6);

        message.setSubject("Your One-Time Password (OTP) for Account Verification");
        message.setText("Dear " + name + ", \n " +
                "Your One-Time Password (OTP) is: " + otpGenerated + "\n" +
                "Please use this OTP to verify your account.\n" +
                "Thank you, \n" +
                "SNZ Incorporation");
        message.setTo(email);

        emailSender.send(message);

        return otpGenerated;

    }

    public static String generateOTP(int length) {
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));
        }
        return otp.toString();
    }
}
