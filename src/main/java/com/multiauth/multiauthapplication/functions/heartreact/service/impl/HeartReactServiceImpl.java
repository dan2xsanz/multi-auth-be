package com.multiauth.multiauthapplication.functions.heartreact.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.enums.NotificationEnum;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.functions.heartreact.dto.HeartReactsDto;
import com.multiauth.multiauthapplication.functions.heartreact.repository.HeartReactRepository;
import com.multiauth.multiauthapplication.functions.heartreact.repository.custom.HeartReactsCustomRepository;
import com.multiauth.multiauthapplication.functions.heartreact.service.HeartReactService;
import com.multiauth.multiauthapplication.functions.notification.dto.NotificationDto;
import com.multiauth.multiauthapplication.functions.notification.service.NotificationService;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;
import com.multiauth.multiauthapplication.model.HeartReacts;
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
public class HeartReactServiceImpl implements HeartReactService {

    @Value(value = "${app.product-image-path}")
    private String productImagePath;

    @Autowired
    private HeartReactsCustomRepository heartReactsCustomRepository;

    @Autowired
    private HeartReactRepository heartReactRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ImageService imageService;

    @Override
    public void addToProductToHeartReactsByAccount(HeartReactsDto heartReactsDto) {

        heartReactsCustomRepository.addToHeartReactsRequest(heartReactsDto);

        // GENERATE NOTIFICATION
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setRead(false);
        notificationDto.setAccountMasterId(heartReactsDto.getAccountMasterId());
        notificationDto.setProductMasterId(heartReactsDto.getProductMasterId());
        notificationDto.setNotifiedAccountMasterId(heartReactsDto.getNotifiedAccountMasterId());
        notificationDto.setNotificationTopic(NotificationEnum.REACTION.getNotificationEnum());

        notificationService.generateNotification(notificationDto);

    }

    @Override
    public void deleteHeartReactsDtoByAccountAndProduct(HeartReactsDto heartReactsDto) {
        heartReactsCustomRepository.deleteHeartReactsRequest(heartReactsDto);

    }

    @Override
    public List<Long> countAllNumberOfHeartReacts(Long productMasterId) {
        return heartReactRepository.countNumberOfHeartReactsByAccountMasterId(productMasterId);

    }

    @Override
    public List<ProductMasterListResponseDto> heartReactListByAccountMasterId(Long accountMasterId) throws IOException {

        List<HeartReacts> listOfHeartReactsByAccount = heartReactRepository.heartReactsByAccountMasterId(accountMasterId);

        List<ProductMasterListResponseDto> productMasterList = new ArrayList<>();

        List<ProductMaster> productMasters = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(listOfHeartReactsByAccount)) {
            for (HeartReacts heartReacts : listOfHeartReactsByAccount) {
                productMasters.add(heartReacts.getProductMaster());
            }
        }

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
                productMaster.setProductCurrency(productMasterDto.getProductCurrency().toString());
                productMaster.setProductCategory(productMasterDto.getProductCategory().toString());
                productMaster.setProductCondition(productMasterDto.getProductCondition().toString());


                productMasterList.add(productMaster);
            }
        }
        return productMasterList;
    }
}
