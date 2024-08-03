package com.multiauth.multiauthapplication.functions.websocket.controller;

import com.multiauth.multiauthapplication.functions.websocket.dto.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationsController {

    @MessageMapping("/notification")
    @SendTo("/topic/notifications")
    public NotificationMessage send(NotificationMessage message) {
        return message;
    }
}
