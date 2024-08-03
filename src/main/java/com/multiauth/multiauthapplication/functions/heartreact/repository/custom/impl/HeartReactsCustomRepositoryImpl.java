package com.multiauth.multiauthapplication.functions.heartreact.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.heartreact.dto.HeartReactsDto;
import com.multiauth.multiauthapplication.functions.heartreact.repository.custom.HeartReactsCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HeartReactsCustomRepositoryImpl implements HeartReactsCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addToHeartReactsRequest(HeartReactsDto heartReactsDto) {

        String insertQuery =
                "INSERT INTO HeartReacts " +
                        "(isHearted, " +
                        "productMasterId, " +
                        "accountMasterId) " +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                heartReactsDto.isHearted(),
                heartReactsDto.getProductMasterId(),
                heartReactsDto.getAccountMasterId());

    }

    @Override
    public void deleteHeartReactsRequest(HeartReactsDto heartReactsDto) {

        String deleteQuery =
                "DELETE FROM HeartReacts " +
                        "WHERE productMasterId = ? " +
                        "AND accountMasterId = ?";

        jdbcTemplate.update(deleteQuery,
                heartReactsDto.getProductMasterId(),
                heartReactsDto.getAccountMasterId());
    }

}