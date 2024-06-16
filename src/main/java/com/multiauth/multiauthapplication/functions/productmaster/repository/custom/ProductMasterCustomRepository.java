package com.multiauth.multiauthapplication.functions.productmaster.repository.custom;

import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;

import java.util.List;

public interface ProductMasterCustomRepository {

    void createNewProduct(ProductMasterDto productMasterDto);

    List<ProductMasterDto> listProductMasters(ProductMasterListRequestDto productMasterListRequestDto);
}
