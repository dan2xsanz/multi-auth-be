package com.multiauth.multiauthapplication.functions.productmaster.repository;

import com.multiauth.multiauthapplication.model.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long> {
}
