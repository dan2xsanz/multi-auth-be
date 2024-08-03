package com.multiauth.multiauthapplication.functions.heartreact.repository;

import com.multiauth.multiauthapplication.model.Favorites;
import com.multiauth.multiauthapplication.model.HeartReacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartReactRepository extends JpaRepository<HeartReacts, Long> {

    @Query(value = "SELECT accountMasterId FROM HeartReacts WHERE productMasterId = :productMasterId", nativeQuery = true)
    List<Long> countNumberOfHeartReactsByAccountMasterId(@Param("productMasterId") Long productMasterId);

    @Query(value = "SELECT * FROM HeartReacts as hearted " +
            "LEFT JOIN ProductMaster as pm ON hearted.productMasterId = pm.id " +
            "WHERE pm.isDeleted = false AND hearted.accountMasterId = :accountMasterId", nativeQuery = true)
    List<HeartReacts> heartReactsByAccountMasterId(@Param("accountMasterId") Long accountMasterId);

}
