package com.multiauth.multiauthapplication.functions.favorites.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesDto {

    @JsonProperty("isFavorite")
    private boolean isFavorite;

    private Long productMasterId;

    private Long accountMasterId;
}
