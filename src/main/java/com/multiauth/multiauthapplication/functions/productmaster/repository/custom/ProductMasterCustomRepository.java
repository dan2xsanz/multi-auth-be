package com.multiauth.multiauthapplication.functions.productmaster.repository.custom;

import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;

public interface ProductMasterCustomRepository {

    void createNewProduct(ProductMasterDto productMasterDto);

    void updateProduct(ProductMasterDto productMasterDto);

}
