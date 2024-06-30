package com.multiauth.multiauthapplication.functions.favorites.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.favorites.dto.FavoritesDto;
import com.multiauth.multiauthapplication.functions.favorites.repository.custom.FavoritesCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FavoritesCustomRepositoryImpl implements FavoritesCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addToFavoritesRequest(FavoritesDto favoritesDto) {

        String insertQuery =
                "INSERT INTO Favorites " +
                        "(isFavorite, " +
                        "productMasterId, " +
                        "accountMasterId) " +
                        "VALUES (?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                favoritesDto.isFavorite(),
                favoritesDto.getProductMasterId(),
                favoritesDto.getAccountMasterId());

    }

    @Override
    public void deleteFavoriteRequest(FavoritesDto favoritesDto) {

        String deleteQuery =
                "DELETE FROM Favorites " +
                        "WHERE productMasterId = ? " +
                        "AND accountMasterId = ?";

        jdbcTemplate.update(deleteQuery,
                favoritesDto.getProductMasterId(),
                favoritesDto.getAccountMasterId()
        );
    }

}