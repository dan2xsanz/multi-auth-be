package com.multiauth.multiauthapplication.util.impl;

import com.multiauth.multiauthapplication.util.CommonUtils;
import com.multiauth.multiauthapplication.util.dto.FindByPropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonUtilsImpl implements CommonUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public <T> T findByProperties(FindByPropertyDto findByPropertyDto, Class<T> returnType) {

        try {
            String mainQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                    findByPropertyDto.getSelectedTableProperty(),
                    findByPropertyDto.getTableName(),
                    findByPropertyDto.getTableProperty());

            Object queryReturn = jdbcTemplate.queryForObject(mainQuery, Object.class, findByPropertyDto.getTablePropertyValue());

            if (queryReturn != null) {
                if (returnType.equals(Long.class)) {
                    return (T) Long.valueOf(queryReturn.toString());
                } else if (returnType.equals(Integer.class)) {
                    return (T) Integer.valueOf(queryReturn.toString());
                } else if (returnType.equals(Boolean.class)) {
                    return (T) Boolean.valueOf(queryReturn.toString());
                } else {
                    return returnType.cast(queryReturn);
                }
            }

        } catch (EmptyResultDataAccessException e) {
            return null;

        }

        return null;
    }
}
