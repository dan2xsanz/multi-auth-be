package com.multiauth.multiauthapplication.functions.productmaster.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;
import com.multiauth.multiauthapplication.functions.productmaster.repository.ProductMasterRepository;
import com.multiauth.multiauthapplication.functions.productmaster.repository.custom.ProductMasterCustomRepository;
import com.multiauth.multiauthapplication.functions.productmaster.service.ProductMasterService;
import com.multiauth.multiauthapplication.model.ProductMaster;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductMasterServiceImpl implements ProductMasterService {

    @Autowired
    private ProductMasterCustomRepository productMasterCustomRepository;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Value(value = "${app.product-image-path}")
    private String productImagePath;

    @Autowired
    private ImageService imageService;

    @Override
    public List<ProductMasterListResponseDto> productMasterListFilter(ProductMasterListRequestDto productMasterListRequestDto) throws IOException {

        List<ProductMasterListResponseDto> productMasterList = new ArrayList<>();

        // FIND LIST OF PRODUCT MASTER BY ACCOUNT
        List<ProductMaster> productMasters = productMasterRepository.findAllProductMasterByFilter(productMasterListRequestDto.getMainCategory(),
                productMasterListRequestDto.getAccountId(), productMasterListRequestDto.getProductCategory(), productMasterListRequestDto.getProductCondition());

        if (ObjectUtils.isNotEmpty(productMasters)) {
            for (ProductMaster productMasterDto : productMasters) {

                ProductMasterListResponseDto productMaster = new ProductMasterListResponseDto();
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

                // JUST IN PRODUCTS
                LocalDateTime createdDate = productMasterDto.getCreatedDate();
                if (createdDate.toLocalDate().isEqual(LocalDate.now())) {
                    productMaster.setJustIn(true);

                }

                productMaster.setItemFor(productMasterDto.getItemFor().toString());
                productMaster.setAccountMasterId(productMasterDto.getAccountMaster().getId());
                productMaster.setProductCurrency(productMasterDto.getProductCurrency().toString());
                productMaster.setProductCategory(productMasterDto.getProductCategory().toString());
                productMaster.setProductCondition(productMasterDto.getProductCondition().toString());


                productMasterList.add(productMaster);
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

    @Override
    public ProductMasterDto updateProduct(ProductMasterDto productMasterRequest) throws IOException {

        ProductMaster productMaster = productMasterRepository.getById(productMasterRequest.getId());

        if (ObjectUtils.isNotEmpty(productMaster)) {

            ProductMasterDto productMasterDto = new ProductMasterDto();
            BeanUtils.copyProperties(productMasterRequest, productMasterDto);

            productMasterDto.setImage1(productMaster.getImage1());
            productMasterDto.setImage2(productMaster.getImage2());
            productMasterDto.setImage3(productMaster.getImage3());
            productMasterDto.setImage4(productMaster.getImage4());

            productMasterDto.setSold(productMasterRequest.isSold());
            productMasterDto.setDeleted(productMasterRequest.isDeleted());

            imageService.uploadImage(Constant.PROD_IMAGE_NAME_1, productImagePath, productMasterDto.getImage1(), productMasterRequest.getImage1());
            imageService.uploadImage(Constant.PROD_IMAGE_NAME_2, productImagePath, productMasterDto.getImage2(), productMasterRequest.getImage2());
            imageService.uploadImage(Constant.PROD_IMAGE_NAME_3, productImagePath, productMasterDto.getImage3(), productMasterRequest.getImage3());
            imageService.uploadImage(Constant.PROD_IMAGE_NAME_4, productImagePath, productMasterDto.getImage4(), productMasterRequest.getImage4());

            productMasterCustomRepository.updateProduct(productMasterDto);

            return productMasterDto;
        }

        return null;
    }
}