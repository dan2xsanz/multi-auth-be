package com.multiauth.multiauthapplication.functions.accountmaster.controller;


import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.accountmaster.service.AccountMasterService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("master-record/user-master")
public class AccountMasterController {

    @Autowired
    private AccountMasterService accountMasterService;

    @PostMapping("create-user")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel createUser(@RequestBody AccountMasterDto accountMasterDto) throws ExemptionError {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(accountMasterService.createNewAccount(accountMasterDto))
                .build();
    }

    @PutMapping("update-user")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel updateUser(@RequestBody AccountMasterDto accountMasterDto) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(accountMasterService.updateAccount(accountMasterDto))
                .build();
    }
}
