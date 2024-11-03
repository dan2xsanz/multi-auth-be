package com.multiauth.multiauthapplication.functions.accountmaster.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.accountmaster.dto.AccountMasterDto;
import com.multiauth.multiauthapplication.functions.accountmaster.repository.custom.AccountMasterCustomRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
                accountMasterDto.getFirstName(),
                accountMasterDto.getLastName(),
                accountMasterDto.getPassword());

    }

    public void updateAccount(AccountMasterDto accountMasterDto) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE AccountMaster SET ");
        List<Object> queryParams = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(accountMasterDto.getFirstName())) {
            queryBuilder.append("firstName = ?, ");
            queryParams.add(accountMasterDto.getFirstName());
        }
        if (ObjectUtils.isNotEmpty(accountMasterDto.getLastName())) {
            queryBuilder.append("lastName = ?, ");
            queryParams.add(accountMasterDto.getLastName());
        }
        if (ObjectUtils.isNotEmpty(accountMasterDto.getPassword())) {
            queryBuilder.append("password = ?, ");
            queryParams.add(accountMasterDto.getPassword());
        }
        if (ObjectUtils.isNotEmpty(accountMasterDto.getCoverImg())) {
            queryBuilder.append("coverImg = ?, ");
            queryParams.add(accountMasterDto.getCoverImg());
        }
        if (ObjectUtils.isNotEmpty(accountMasterDto.getProfileImg())) {
            queryBuilder.append("profileImg = ?, ");
            queryParams.add(accountMasterDto.getProfileImg());
        }

        // Remove the last comma and space from the query
        if (queryBuilder.toString().endsWith(", ")) {
            queryBuilder.setLength(queryBuilder.length() - 2);
        }

        // Append the WHERE clause
        queryBuilder.append(" WHERE id = ?");
        queryParams.add(accountMasterDto.getId());

        String insertQuery = queryBuilder.toString();

        // Execute the update with the dynamically built query and parameters
        jdbcTemplate.update(insertQuery, queryParams.toArray());

    }
}
