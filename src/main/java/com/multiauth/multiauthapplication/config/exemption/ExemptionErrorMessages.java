package com.multiauth.multiauthapplication.config.exemption;

public enum ExemptionErrorMessages implements ExemptionErrorMessageBase {

    ALREADY_HAVE_ACCOUNT("AccountExist", "Email already exist, Please try another email."),

    OTP_INVALID("InvalidOtp", "Invalid OTP, Please try again."),

    EMAIL_NOT_FOUND("EmailNotFound", "Email Not Found, Please make sure email is registered."),

    INVALID_CREDENTIALS("InvalidCredentials", "Invalid Credentials, Please try again.");

    private final String code;

    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    ExemptionErrorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
