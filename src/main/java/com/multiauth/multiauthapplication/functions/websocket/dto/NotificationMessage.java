package com.multiauth.multiauthapplication.functions.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {

    private String notificationTopic;

    private Long receiverId;

    private String content;

    private Long senderId;
}
