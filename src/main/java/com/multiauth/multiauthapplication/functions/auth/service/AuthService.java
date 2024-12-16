package com.multiauth.multiauthapplication.functions.auth.service;


import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginResponseDto;
import com.multiauth.multiauthapplication.model.AccountMaster;

import java.io.IOException;

public interface AuthService {

    LoginResponseDto loginRequest(LoginRequestDto loginRequestDto) throws ExemptionError, IOException;
}
