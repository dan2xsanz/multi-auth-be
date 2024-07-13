package com.multiauth.multiauthapplication.functions.favorites.service.impl;

import com.multiauth.multiauthapplication.common.constant.Constant;
import com.multiauth.multiauthapplication.common.image.ImageService;
import com.multiauth.multiauthapplication.functions.favorites.dto.FavoritesDto;
import com.multiauth.multiauthapplication.functions.favorites.repository.FavoritesRepository;
import com.multiauth.multiauthapplication.functions.favorites.repository.custom.FavoritesCustomRepository;
import com.multiauth.multiauthapplication.functions.favorites.service.FavoritesService;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;
import com.multiauth.multiauthapplication.model.Favorites;
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
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesCustomRepository favoritesCustomRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Value(value = "${app.product-image-path}")
    private String productImagePath;

    @Autowired
    private ImageService imageService;

    @Override
    public void addToProductToFavoritesByAccount(FavoritesDto favoritesDto) {

        favoritesCustomRepository.addToFavoritesRequest(favoritesDto);

    }

    @Override
    public void deleteFavoriteByAccountAndProduct(FavoritesDto favoritesDto) {
        favoritesCustomRepository.deleteFavoriteRequest(favoritesDto);

    }

    @Override
    public List<Long> countAllNumberOfFavorites(Long productMasterId) {
        return favoritesRepository.countNumberOfFavoritesByAccountMasterId(productMasterId);

    }

    @Override
    public List<ProductMasterListResponseDto> favoritesListByAccountMasterId(Long accountMasterId) throws IOException {

        List<Favorites> listOfFavoritesByAccount = favoritesRepository.favoritesByAccountMasterId(accountMasterId);

        List<ProductMasterListResponseDto> productMasterList = new ArrayList<>();

        List<ProductMaster> productMasters = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(listOfFavoritesByAccount)) {
            for (Favorites favorites : listOfFavoritesByAccount) {
                productMasters.add(favorites.getProductMaster());
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
