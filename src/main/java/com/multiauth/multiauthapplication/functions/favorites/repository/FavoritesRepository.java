package com.multiauth.multiauthapplication.functions.favorites.repository;

import com.multiauth.multiauthapplication.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query(value = "SELECT accountMasterId FROM Favorites WHERE productMasterId = :productMasterId", nativeQuery = true)
    List<Long> countNumberOfFavoritesByAccountMasterId(@Param("productMasterId") Long productMasterId);

    @Query(value = "SELECT * FROM Favorites as fav " +
            "LEFT JOIN ProductMaster as pm ON fav.productMasterId = pm.id " +
            "WHERE pm.isDeleted = false AND fav.accountMasterId = :accountMasterId", nativeQuery = true)
    List<Favorites> favoritesByAccountMasterId(@Param("accountMasterId") Long accountMasterId);

}
