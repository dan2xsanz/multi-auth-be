package com.multiauth.multiauthapplication.functions.notification.controller;


import com.multiauth.multiauthapplication.functions.notification.service.NotificationService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("master-record/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("notifications-by-account/{accountMasterId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel accountNotifications(@PathVariable Long accountMasterId) {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(notificationService.getAllNotificationByAccountMaster(accountMasterId))
                .build();
    }
}
