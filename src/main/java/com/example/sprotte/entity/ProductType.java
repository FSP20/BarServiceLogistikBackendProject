package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.constants.ProductTypeConstant;
import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.errorhandling.product.ProductTypeNotSupportedException;

import javax.persistence.*;

@Entity
@Table(name= DatabaseConstants.PRODUCT_TYPE_TABLE)
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=DatabaseConstants.ID_PRODUCT_TYPE)
    private Long id;

    @Column(name=DatabaseConstants.PRODUCT_TYPE)
    private String productType;

    @Column(name=DatabaseConstants.PRODUCT_TYPE_DESCRIPTION)
    private String description;

    public ProductType() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {

        if (checkProductTypeContained(productType) == false){
            throw new ProductTypeNotSupportedException(ResponseMessageConstants.PRODUCT_TYPE_NOT_SUPPORTED
                    + "(" + productType + ")");
        }

        this.productType = productType;
    }

    private static boolean checkProductTypeContained(String productTypeToCheck) {

        if (productTypeToCheck == null || productTypeToCheck.trim().equals("")){
            return false;
        }

        for (ProductTypeConstant productTypeConstant : ProductTypeConstant.values()) {
            if (productTypeConstant.toString().equals(productTypeToCheck)) {
                return true;
            }
        }

        return false;
    }
}
