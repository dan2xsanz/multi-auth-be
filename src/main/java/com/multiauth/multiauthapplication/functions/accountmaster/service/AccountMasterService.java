package com.multiauth.multiauthapplication.functions.accountmaster.service;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;

public interface AccountMasterService {

    AccountMasterDto createNewAccount(AccountMasterDto accountMasterDto) throws ExemptionError;
}
