package com.multiauth.multiauthapplication.functions.productmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMasterListRequestDto {

    private Long mainCategory;

    private Long accountId;

    private Long productCategory;

    private Long productCondition;
}
