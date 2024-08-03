package com.multiauth.multiauthapplication.functions.heartreact.service;

import com.multiauth.multiauthapplication.functions.heartreact.dto.HeartReactsDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;

import java.io.IOException;
import java.util.List;

public interface HeartReactService {

    List<Long> countAllNumberOfHeartReacts(Long productMasterId);

    void addToProductToHeartReactsByAccount(HeartReactsDto heartReactsDto);

    void deleteHeartReactsDtoByAccountAndProduct(HeartReactsDto heartReactsDto);

    List<ProductMasterListResponseDto> heartReactListByAccountMasterId(Long accountMasterId) throws IOException;

}