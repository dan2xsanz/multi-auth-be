package com.multiauth.multiauthapplication.functions.accountmaster.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountMasterDto {

    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("profileImg")
    private String profileImg;

    @JsonProperty("coverImg")
    private String coverImg;

    private String email;

    private String password;

    private String otp;

    @JsonProperty("isForgotPass")
    private boolean isForgotPass;

}
