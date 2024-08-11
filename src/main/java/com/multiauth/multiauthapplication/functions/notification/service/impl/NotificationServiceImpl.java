package com.multiauth.multiauthapplication.functions.notification.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.enums.NotificationEnum;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;
import com.multiauth.multiauthapplication.functions.notification.repository.NotificationRepository;
import com.multiauth.multiauthapplication.functions.notification.repository.custom.NotificationCustomRepository;
import com.multiauth.multiauthapplication.functions.notification.service.NotificationService;
import com.multiauth.multiauthapplication.model.Notification;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value(value = "${app.product-image-path}")
    private String productImagePath;

    @Autowired
    private NotificationCustomRepository notificationCustomRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public void generateNotification(NotificationDto notificationDto) {
        notificationCustomRepository.addNewNotification(notificationDto);

    }

    @Override
    public List<NotificationDto> getAllNotificationByAccountMaster(Long accountMasterId) throws IOException {

        List<Notification> notificationList = notificationRepository.notificationByAccountMasterId(accountMasterId);

        List<NotificationDto> notificationListResponse = new ArrayList<>();

        for (Notification notification : notificationList) {

            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);

            notificationDto.setAccountMasterId(notification.getAccountMaster().getId());
            notificationDto.setProductMasterId(notification.getProductMaster().getId());
            notificationDto.setNotifiedAccountMasterId(notification.getNotifiedAccountMaster().getId());
            notificationDto.setNotificationSubject(notification.getAccountMaster().getFirstName() + " " + notification.getAccountMaster().getLastName());
            notificationDto.setNotificationTopic(notification.getNotificationTopic());
            notificationDto.setNotificationDetails(getNotificationDetails(notification));
            notificationDto.setNotificationProduct(notification.getProductMaster().getProductName());

            // NOTIFICATION IMAGE
            if (ObjectUtils.isNotEmpty(notification.getProductMaster().getImage1())) {
                notificationDto.setNotificationImage(
                        imageService.getUploadImage(Constant.PROD_IMAGE_NAME_1, productImagePath, notification.getProductMaster().getImage1()));

            }

            notificationListResponse.add(notificationDto);
        }


        return notificationListResponse;
    }

    @Override
    public void readNotificationByAccountMasterId(NotificationDto notificationDto) {
        notificationCustomRepository.readNotification(notificationDto);

    }

    private String getNotificationDetails(Notification notification) {

        String notificationDetails = "";
        if (Objects.equals(notification.getNotificationTopic(), NotificationEnum.REACTION.getNotificationEnum())) {
            notificationDetails = "Loves your product " + notification.getProductMaster().getProductName();

        }

        if (Objects.equals(notification.getNotificationTopic(), NotificationEnum.COMMENT.getNotificationEnum())) {
            notificationDetails = "Commented your product " + notification.getProductMaster().getProductName();

        }
        return notificationDetails;
    }
}