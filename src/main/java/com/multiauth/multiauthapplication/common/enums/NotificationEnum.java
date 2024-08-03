package com.multiauth.multiauthapplication.common.enums;

import lombok.Getter;

@Getter
public enum NotificationEnum {

    REACTION("REACTION"),

    MESSAGE("MESSAGE"),

    COMMENT("COMMENT");

    private final String notificationEnum;

    NotificationEnum(String notificationEnum) {
        this.notificationEnum = notificationEnum;
    }


}