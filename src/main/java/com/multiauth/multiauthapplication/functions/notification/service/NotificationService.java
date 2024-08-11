package com.multiauth.multiauthapplication.functions.notification.service;

import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;

import java.io.IOException;
import java.util.List;

public interface NotificationService {

    void generateNotification(NotificationDto notificationDto);

    List<NotificationDto> getAllNotificationByAccountMaster(Long accountMasterId) throws IOException;

    void readNotificationByAccountMasterId(NotificationDto notificationDto);
}
