package com.multiauth.multiauthapplication.functions.accountmaster.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.custom.AccountMasterCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountMasterCustomCustomRepositoryImpl implements AccountMasterCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createNewAccount(AccountMasterDto accountMasterDto) {

        String insertQuery =
                "INSERT INTO AccountMaster " +
                        "(email, " +
                        "firstName, " +
                        "lastName, " +
                        "password) " +
                        "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                accountMasterDto.getEmail(),
                accountMasterDto.getFName(),
                accountMasterDto.getLName(),
                accountMasterDto.getPassword());

    }
}
