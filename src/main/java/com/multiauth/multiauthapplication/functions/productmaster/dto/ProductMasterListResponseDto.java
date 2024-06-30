package com.multiauth.multiauthapplication.functions.productmaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMasterListResponseDto {

    private Long id;

    @JsonProperty("image1")
    private String image1;

    @JsonProperty("image2")
    private String image2;

    @JsonProperty("image3")
    private String image3;

    @JsonProperty("image4")
    private String image4;

    @JsonProperty("productName")
    private String productName;

    private String productCurrency;

    @JsonProperty("productPrice")
    private String productPrice;

    private Integer productDiscount;

    private String itemFor;

    private String productCategory;

    private String productCondition;

    @JsonProperty("productDescription")
    private String productDescription;

    private String productLocation;

    private Long accountMasterId;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("isSold")
    private boolean isSold;

    @JsonProperty("justIn")
    private boolean justIn;
}
