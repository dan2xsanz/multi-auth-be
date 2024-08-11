package com.multiauth.multiauthapplication.functions.heartreact.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiauth.multiauthapplication.common.dto.NotificationDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HeartReactsDto extends NotificationDetailsDto {

    @JsonProperty("isHearted")
    private boolean isHearted;

    private Long productMasterId;

    private Long accountMasterId;
}
