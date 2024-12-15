package com.multiauth.multiauthapplication.functions.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String profileImg;

    private String coverImg;

    // JWT
    private String token;

    private String refreshToken;

    private String expirationTime;
}
