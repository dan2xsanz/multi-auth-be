package com.multiauth.multiauthapplication.functions.productmaster.controller;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;
import com.multiauth.multiauthapplication.functions.productmaster.service.ProductMasterService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("master-record/product-master")
public class ProductMasterController {

    @Autowired
    private ProductMasterService productMasterService;

    @GetMapping("product-by-id/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getProductById(@PathVariable("productId") Long productId) throws IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(productMasterService.getProductById(productId))
                .build();
    }

    @PostMapping("product-by-filter")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel getProductsByUser(@RequestBody ProductMasterListRequestDto productMasterListRequestDto) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(productMasterService.productMasterListFilter(productMasterListRequestDto))
                .build();
    }

    @PostMapping("create-product")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel createProduct(@RequestBody ProductMasterDto productMasterDto) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(productMasterService.createProduct(productMasterDto))
                .build();
    }

    @PostMapping("update-product")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel updateProduct(@RequestBody ProductMasterDto productMasterDto) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(productMasterService.updateProduct(productMasterDto))
                .build();
    }

}
