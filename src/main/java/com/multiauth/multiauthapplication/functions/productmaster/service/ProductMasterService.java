package com.multiauth.multiauthapplication.functions.productmaster.service;

import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;

import java.io.IOException;
import java.util.List;

public interface ProductMasterService {

    ProductMasterDto getProductById(Long productId) throws IOException;

    List<ProductMasterListResponseDto> productMasterListFilter(ProductMasterListRequestDto productMasterListRequestDto) throws IOException;

    ProductMasterDto createProduct(ProductMasterDto productMasterDto) throws IOException;

    ProductMasterDto updateProduct(ProductMasterDto productMasterDto) throws IOException;

}
