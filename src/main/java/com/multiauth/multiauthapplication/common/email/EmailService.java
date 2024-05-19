package com.multiauth.multiauthapplication.common.email;

public interface EmailService {

    String sendOTPEmail(String email, String name);
}
