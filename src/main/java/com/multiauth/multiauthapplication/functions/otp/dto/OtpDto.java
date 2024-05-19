package com.multiauth.multiauthapplication.functions.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {

    private Long id;

    private LocalDateTime expiryDateTime;

    private String otp;

    private String username;
}
