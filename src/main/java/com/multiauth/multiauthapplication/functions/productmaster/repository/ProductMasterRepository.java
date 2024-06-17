package com.multiauth.multiauthapplication.functions.productmaster.repository;

import com.multiauth.multiauthapplication.model.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {


    @Query(value = "SELECT * "
            + "FROM ProductMaster "
            + "WHERE accountMasterId = :accountMasterId "
            + "AND isDeleted = false ", nativeQuery = true)
    List<ProductMaster> findAlProductMasterByAccount(@Param("accountMasterId") Long accountMasterId);
}
