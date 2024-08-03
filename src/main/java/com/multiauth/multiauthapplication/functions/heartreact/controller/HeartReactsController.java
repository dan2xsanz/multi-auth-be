package com.multiauth.multiauthapplication.functions.heartreact.controller;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.heartreact.dto.HeartReactsDto;
import com.multiauth.multiauthapplication.functions.heartreact.service.HeartReactService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("master-record/heart-reacts")
public class HeartReactsController {

    @Autowired
    private HeartReactService heartReactService;

    @PostMapping("heart-reacts-by-product/{productMasterId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel countHeartReact(@PathVariable Long productMasterId) throws ExemptionError {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(heartReactService.countAllNumberOfHeartReacts(productMasterId))
                .build();
    }

    @PostMapping("add-heart-reacts")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel addHeartReacts(@RequestBody HeartReactsDto heartReactsDto) throws ExemptionError {
        heartReactService.addToProductToHeartReactsByAccount(heartReactsDto);
        return ApiResultModel.builder()
                .isSuccess(true)
                .build();
    }

    @PutMapping("delete-heart-reacts")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel deleteHeartReact(@RequestBody HeartReactsDto heartReactsDto) throws ExemptionError {
        heartReactService.deleteHeartReactsDtoByAccountAndProduct(heartReactsDto);
        return ApiResultModel.builder()
                .isSuccess(true)
                .build();
    }

    @PostMapping("heart-reacts-by-account/{accountMasterId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel accountHeartReacts(@PathVariable Long accountMasterId) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(heartReactService.heartReactListByAccountMasterId(accountMasterId))
                .build();
    }
}
