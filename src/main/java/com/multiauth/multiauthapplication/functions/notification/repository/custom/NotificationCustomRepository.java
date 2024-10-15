package com.multiauth.multiauthapplication.functions.notification.repository.custom;

import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;

public interface NotificationCustomRepository {

    void addNewNotification(NotificationDto notificationDto);

    void readNotification(NotificationDto notificationDto);
}
