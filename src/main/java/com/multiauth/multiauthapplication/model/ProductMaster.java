package com.multiauth.multiauthapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductMaster")
public class ProductMaster extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("image1")
    private String image1;

    @JsonProperty("image2")
    private String image2;

    @JsonProperty("image3")
    private String image3;

    @JsonProperty("image4")
    private String image4;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("productPrice")
    private String productPrice;

    private Long productCategory;

    private Long productCondition;

    @JsonProperty("productDescription")
    private String productDescription;

    private String productLocation;

    @ManyToOne
    @JoinColumn(name = "accountMasterId", nullable = false)
    @JsonBackReference
    private AccountMaster accountMaster;

}
