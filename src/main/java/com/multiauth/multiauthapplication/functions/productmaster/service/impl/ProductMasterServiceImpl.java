package com.multiauth.multiauthapplication.functions.productmaster.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;
import com.multiauth.multiauthapplication.functions.productmaster.repository.custom.ProductMasterCustomRepository;
import com.multiauth.multiauthapplication.functions.productmaster.service.ProductMasterService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    @Autowired
    private ProductMasterCustomRepository productMasterCustomRepository;

    @Value(value = "${app.product-image-path}")
    private String productImagePath;

    @Autowired
    private ImageService imageService;

    @Override
    public List<ProductMasterDto> productMasterList(ProductMasterListRequestDto productMasterListRequestDto) throws IOException {

        List<ProductMasterDto> productMasterList = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(productMasterListRequestDto.getAccountId())) {

            List<ProductMasterDto> productMasters = productMasterCustomRepository.listProductMasters(productMasterListRequestDto);

            if (ObjectUtils.isNotEmpty(productMasters)) {
                for (ProductMasterDto productMasterDto : productMasters) {

                    ProductMasterDto productMaster = new ProductMasterDto();
                    BeanUtils.copyProperties(productMasterDto, productMaster);

                    // IMAGE 1
                    if (ObjectUtils.isNotEmpty(productMasterDto.getImage1())) {
                        productMaster.setImage1(
                                imageService.getUploadImage(Constant.PROD_IMAGE_NAME_1, productImagePath, productMasterDto.getImage1()));

                    }
                    // IMAGE 2
                    if (ObjectUtils.isNotEmpty(productMasterDto.getImage2())) {
                        productMaster.setImage2(
                                imageService.getUploadImage(Constant.PROD_IMAGE_NAME_2, productImagePath, productMasterDto.getImage2()));

                    }
                    // IMAGE 3
                    if (ObjectUtils.isNotEmpty(productMasterDto.getImage3())) {
                        productMaster.setImage3(
                                imageService.getUploadImage(Constant.PROD_IMAGE_NAME_3, productImagePath, productMasterDto.getImage3()));

                    }
                    // IMAGE 3
                    if (ObjectUtils.isNotEmpty(productMasterDto.getImage4())) {
                        productMaster.setImage4(
                                imageService.getUploadImage(Constant.PROD_IMAGE_NAME_4, productImagePath, productMasterDto.getImage4()));

                    }

                    productMasterList.add(productMaster);
                }
            }
        }
        return productMasterList;
    }

    @Override
    public ProductMasterDto createProduct(ProductMasterDto productMasterRequest) throws IOException {

        ProductMasterDto productMasterDto = new ProductMasterDto();
        BeanUtils.copyProperties(productMasterRequest, productMasterDto);

        productMasterDto.setImage1(imageService.generateFileName());
        productMasterDto.setImage2(imageService.generateFileName());
        productMasterDto.setImage3(imageService.generateFileName());
        productMasterDto.setImage4(imageService.generateFileName());

        productMasterCustomRepository.createNewProduct(productMasterDto);

        imageService.uploadImage(Constant.PROD_IMAGE_NAME_1, productImagePath, productMasterDto.getImage1(), productMasterRequest.getImage1());
        imageService.uploadImage(Constant.PROD_IMAGE_NAME_2, productImagePath, productMasterDto.getImage2(), productMasterRequest.getImage2());
        imageService.uploadImage(Constant.PROD_IMAGE_NAME_3, productImagePath, productMasterDto.getImage3(), productMasterRequest.getImage3());
        imageService.uploadImage(Constant.PROD_IMAGE_NAME_4, productImagePath, productMasterDto.getImage4(), productMasterRequest.getImage4());

        return productMasterDto;
    }
}