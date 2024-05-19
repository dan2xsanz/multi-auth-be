package com.multiauth.multiauthapplication.functions.auth.service;


import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.model.AccountMaster;

public interface AuthService {

    AccountMaster loginRequest(LoginRequestDto loginRequestDto) throws ExemptionError;
}
