package com.multiauth.multiauthapplication.functions.heartreact.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeartReactsDto {

    @JsonProperty("isHearted")
    private boolean isHearted;

    private Long productMasterId;

    private Long accountMasterId;
}
