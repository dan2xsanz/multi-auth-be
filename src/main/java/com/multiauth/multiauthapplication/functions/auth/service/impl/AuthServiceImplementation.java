package com.multiauth.multiauthapplication.functions.auth.service.impl;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.exemption.ExemptionErrorMessages;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.AccountMasterRepository;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.service.AuthService;
import com.multiauth.multiauthapplication.model.AccountMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private AccountMasterRepository accountMasterRepository;

    public AccountMaster loginRequest(LoginRequestDto loginRequestDto) throws ExemptionError {

        // VALIDATE USERNAME AND PASSWORD
        AccountMaster accountMaster = accountMasterRepository.validateAccountMaster(loginRequestDto.getUsername(), loginRequestDto.getPassword()).orElse(null);

        // THROW INVALID CREDENTIALS IF EMPTY
        if (ObjectUtils.isEmpty(accountMaster)) {
            throw new ExemptionError(ExemptionErrorMessages.INVALID_CREDENTIALS);
        }

        // RETURN ACCOUNT
        return accountMaster;
    }
}
