package com.multiauth.multiauthapplication.functions.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private String email;

    private String password;

    // JWT
    private String token;

    private String refreshToken;

    private String expirationTime;
}
