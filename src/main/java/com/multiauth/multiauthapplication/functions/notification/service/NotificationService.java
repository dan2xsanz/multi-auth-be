package com.multiauth.multiauthapplication.functions.notification.service;

import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    void generateNotification(NotificationDto notificationDto);

    List<NotificationDto> getAllNotificationByAccountMaster(Long accountMasterId);
}
