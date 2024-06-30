package com.multiauth.multiauthapplication.functions.favorites.service;

import com.multiauth.multiauthapplication.functions.favorites.dto.FavoritesDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListResponseDto;

import java.io.IOException;
import java.util.List;

public interface FavoritesService {

    void addToProductToFavoritesByAccount(FavoritesDto favoritesDto);

    void deleteFavoriteByAccountAndProduct(FavoritesDto favoritesDto);

    List<Long> countAllNumberOfFavorites(Long productMasterId);

    List<ProductMasterListResponseDto> favoritesListByAccountMasterId(Long accountMasterId) throws IOException;
}
