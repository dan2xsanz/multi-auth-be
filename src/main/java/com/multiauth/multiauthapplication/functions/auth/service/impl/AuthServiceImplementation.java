package com.multiauth.multiauthapplication.functions.auth.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.exemption.ExemptionErrorMessages;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.AccountMasterRepository;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.service.AuthService;
import com.multiauth.multiauthapplication.model.AccountMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Service
public class AuthServiceImplementation implements AuthService {

    @Value(value = "${app.account-image-path}")
    private String accountImagePath;

    @Autowired
    private AccountMasterRepository accountMasterRepository;

    @Autowired
    private ImageService imageService;

    public AccountMaster loginRequest(LoginRequestDto loginRequestDto) throws ExemptionError, IOException {

        // VALIDATE USERNAME AND PASSWORD
        AccountMaster accountMaster = accountMasterRepository.validateAccountMaster(loginRequestDto.getUsername(), loginRequestDto.getPassword()).orElse(null);

        // THROW INVALID CREDENTIALS IF EMPTY
        if (ObjectUtils.isEmpty(accountMaster)) {
            throw new ExemptionError(ExemptionErrorMessages.INVALID_CREDENTIALS);

        }

        // IMAGE COVER PHOTO
        if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(accountMaster.getCoverImg())) {
            accountMaster.setCoverImg(
                    imageService.getUploadImage(Constant.PROD_IMAGE_NAME_1, accountImagePath, accountMaster.getCoverImg()));

        }

        // RETURN ACCOUNT
        return accountMaster;
    }
}
