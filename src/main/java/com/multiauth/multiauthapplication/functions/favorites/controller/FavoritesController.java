package com.multiauth.multiauthapplication.functions.favorites.controller;

import com.multiauth.multiauthapplication.config.exemption.ExemptionError;
import com.multiauth.multiauthapplication.functions.favorites.dto.FavoritesDto;
import com.multiauth.multiauthapplication.functions.favorites.service.FavoritesService;
import com.multiauth.multiauthapplication.model.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("master-record/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("favorites-by-product/{productMasterId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel countFavorites(@PathVariable Long productMasterId) throws ExemptionError {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(favoritesService.countAllNumberOfFavorites(productMasterId))
                .build();
    }

    @PostMapping("add-favorite")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel addFavorite(@RequestBody FavoritesDto favoritesDto) throws ExemptionError {
        favoritesService.addToProductToFavoritesByAccount(favoritesDto);
        return ApiResultModel.builder()
                .isSuccess(true)
                .build();
    }

    @PutMapping("delete-favorite")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel deleteFavorite(@RequestBody FavoritesDto favoritesDto) throws ExemptionError {
        favoritesService.deleteFavoriteByAccountAndProduct(favoritesDto);
        return ApiResultModel.builder()
                .isSuccess(true)
                .build();
    }

    @PostMapping("favorites-by-account/{accountMasterId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResultModel accountFavorites(@PathVariable Long accountMasterId) throws ExemptionError, IOException {
        return ApiResultModel.builder()
                .isSuccess(true)
                .resultData(favoritesService.favoritesListByAccountMasterId(accountMasterId))
                .build();
    }
}
