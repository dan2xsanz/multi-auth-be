package com.multiauth.multiauthapplication.functions.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String username;

    private String password;

    // JWT
    private String token;

    private String refreshToken;

    private String expirationTime;
}
