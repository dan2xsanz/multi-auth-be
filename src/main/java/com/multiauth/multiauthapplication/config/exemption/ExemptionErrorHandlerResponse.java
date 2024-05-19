package com.multiauth.multiauthapplication.config.exemption;

import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExemptionErrorHandlerResponse {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ApiResultModel handleApiException(final Exception exception,
                                                           final HttpServletRequest request) {

        String errorMessage = exception.getMessage();

        return ApiResultModel.builder()
                .isSuccess(false)
                .errorMessages(null)
                .message(errorMessage)
                .exceptionType("single")
                .build();
    }


    @ExceptionHandler(ExemptionError.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ApiResultModel handleCustomException(ExemptionError exemptionError) {

        String errorMessage = exemptionError.getMessage();

        return ApiResultModel.builder()
                .isSuccess(false)
                .errorMessages(null)
                .message(errorMessage)
                .exceptionType("single")
                .errorCodes(exemptionError.getMessageCode())
                .build();
    }
}
