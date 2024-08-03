package com.multiauth.multiauthapplication.functions.notification.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;
import com.multiauth.multiauthapplication.functions.notification.repository.custom.NotificationCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationCustomRepositoryImpl implements NotificationCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addNewNotification(NotificationDto notificationDto) {

        String insertQuery =
                "INSERT INTO Notification " +
                        "(isRead, " +
                        "notificationTopic, " +
                        "productMasterId, " +
                        "accountMasterId) " +
                        "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                notificationDto.isRead(),
                notificationDto.getNotificationTopic(),
                notificationDto.getProductMasterId(),
                notificationDto.getAccountMasterId());

    }
}