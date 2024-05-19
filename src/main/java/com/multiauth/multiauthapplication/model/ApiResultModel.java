package com.multiauth.multiauthapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ApiResultModel {
    private Boolean isSuccess;

    private String message;

    private Object messageParams;

    private Object resultData;

    private List<String> errorMessages;

    private Object errorCodes;

    private String exceptionType;

}
