package com.multiauth.multiauthapplication.functions.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiauth.multiauthapplication.common.dto.NotificationDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto extends NotificationDetailsDto {

    private Long id;

    @JsonProperty("isRead")
    private boolean isRead;

    private String notificationTopic;

    private Long productMasterId;

    private Long accountMasterId;

    private String notificationImage;

    private String notificationSubject;

    private String notificationDetails;

    private String notificationProduct;
}