package com.multiauth.multiauthapplication.functions.accountmaster.repository.custom;

import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;

public interface AccountMasterCustomRepository {

    void createNewAccount(AccountMasterDto accountMasterDto);
}
