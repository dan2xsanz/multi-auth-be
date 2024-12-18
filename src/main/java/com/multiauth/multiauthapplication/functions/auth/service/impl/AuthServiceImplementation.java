package com.multiauth.multiauthapplication.functions.auth.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.config.utils.JWTUtils;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.AccountMasterRepository;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginRequestDto;
import com.multiauth.multiauthapplication.functions.auth.dto.LoginResponseDto;
import com.multiauth.multiauthapplication.functions.auth.service.AuthService;
import com.multiauth.multiauthapplication.model.AccountMaster;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class AuthServiceImplementation implements AuthService {

    @Value(value = "${app.account-image-path}")
    private String accountImagePath;

    @Autowired
    private AccountMasterRepository accountMasterRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private ImageService imageService;

    public LoginResponseDto loginRequest(LoginRequestDto loginRequestDto) throws ExemptionError, IOException {

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        AccountMaster accountMaster = accountMasterRepository.validateAccountMaster(loginRequestDto.getUsername()).orElse(null);

        if (ObjectUtils.isNotEmpty(accountMaster)) {
            BeanUtils.copyProperties(accountMaster, loginResponseDto);

            loginResponseDto.setToken(jwtUtils.generateToken(accountMaster));
            loginResponseDto.setRefreshToken(jwtUtils.generateRefreshToken(new HashMap<>(), accountMaster));

            // IMAGE COVER PHOTO
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(accountMaster.getCoverImg())) {
                loginResponseDto.setCoverImg(
                        imageService.getUploadImage(Constant.COVER_PHOTO, accountImagePath, accountMaster.getCoverImg()));

            }

            // IMAGE COVER PHOTO
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(accountMaster.getProfileImg())) {
                loginResponseDto.setProfileImg(
                        imageService.getUploadImage(Constant.PROFILE, accountImagePath, accountMaster.getProfileImg()));

            }

        }

        return loginResponseDto;
    }
}
