package com.multiauth.multiauthapplication.functions.productmaster.repository.custom.impl;

import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterDto;
import com.multiauth.multiauthapplication.functions.productmaster.dto.ProductMasterListRequestDto;
import com.multiauth.multiauthapplication.functions.productmaster.repository.custom.ProductMasterCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductMasterCustomRepositoryImpl implements ProductMasterCustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createNewProduct(ProductMasterDto productMasterDto) {

        String insertQuery =
                "INSERT INTO ProductMaster " +
                        "(image1, " +
                        "image2, " +
                        "image3, " +
                        "image4, " +
                        "productName, " +
                        "productPrice, " +
                        "productCategory, " +
                        "productCondition, " +
                        "productDescription, " +
                        "productLocation, " +
                        "accountMasterId) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                productMasterDto.getImage1(),
                productMasterDto.getImage2(),
                productMasterDto.getImage3(),
                productMasterDto.getImage4(),
                productMasterDto.getProductName(),
                productMasterDto.getProductPrice(),
                productMasterDto.getProductCondition(),
                productMasterDto.getProductCondition(),
                productMasterDto.getProductDescription(),
                productMasterDto.getProductLocation(),
                productMasterDto.getAccountMasterId()
        );
    }

    @Override
    public List<ProductMasterDto> listProductMasters(ProductMasterListRequestDto productMasterListRequestDto) {

        String whereClause = String.format("WHERE accountMasterId = '%s' ", productMasterListRequestDto.getAccountId());

        // GET ALL PRODUCT QUERY
        String mainQuery = "SELECT " +
                "id, " +
                "image1, " +
                "image2, " +
                "image3, " +
                "image4, " +
                "productName, " +
                "productPrice, " +
                "productCategory, " +
                "productCondition, " +
                "productDescription, " +
                "productLocation, " +
                "accountMasterId " +
                "FROM ProductMaster " +
                whereClause;

        // RETRIEVE PAGINATED SALES TRANSACTION SYNCING DTO FROM THE DATABASE
        return jdbcTemplate.query(mainQuery, new BeanPropertyRowMapper<>(ProductMasterDto.class));
    }
}
