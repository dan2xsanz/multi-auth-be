package com.multiauth.multiauthapplication.functions.commentsectionmaster.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.commentsectionmaster.dto.CommentSectionDto;
import com.multiauth.multiauthapplication.functions.commentsectionmaster.repository.custom.CommentSectionCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentSectionCustomRepositoryImpl implements CommentSectionCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addNewComment(CommentSectionDto commentSectionDto) {

        String insertQuery =
                "INSERT INTO CommentSectionMaster " +
                        "(comment, " +
                        "productMasterId, " +
                        "accountMasterId) " +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                commentSectionDto.getComment(),
                commentSectionDto.getProductMasterId(),
                commentSectionDto.getAccountMasterId());

    }
}
