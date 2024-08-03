package com.multiauth.multiauthapplication.functions.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;

    @JsonProperty("isRead")
    private boolean isRead;

    private String notificationTopic;

    private Long productMasterId;

    private Long accountMasterId;

    private String notificationSubject;

    private String notificationDetails;
}