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
            + "WHERE isDeleted = false "
            + "AND (:mainCategory is null OR (itemFor = :mainCategory "
            + "    OR (:mainCategory = 6 AND createdDate >= CURRENT_DATE))) "
            + "AND (:accountId is null OR accountMasterId = :accountId) "
            + "AND (:productCategory is null OR productCategory = :productCategory) "
            + "AND (:productCondition is null OR productCondition = :productCondition) "
            + "ORDER BY createdDate ASC ", nativeQuery = true)
    List<ProductMaster> findAllProductMasterByFilter(@Param("mainCategory") Long mainCategory,
                                                     @Param("accountId") Long accountId,
                                                     @Param("productCategory") Long productCategory,
                                                     @Param("productCondition") Long productCondition);

}
