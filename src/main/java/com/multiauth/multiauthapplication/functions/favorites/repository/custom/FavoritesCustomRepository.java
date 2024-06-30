package com.multiauth.multiauthapplication.functions.favorites.repository.custom;

import com.multiauth.multiauthapplication.functions.favorites.dto.FavoritesDto;

public interface FavoritesCustomRepository {

    void addToFavoritesRequest(FavoritesDto favoritesDto);

    void deleteFavoriteRequest(FavoritesDto favoritesDto);
}
