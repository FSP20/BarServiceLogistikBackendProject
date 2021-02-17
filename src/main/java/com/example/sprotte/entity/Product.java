package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;

import javax.persistence.*;

@Entity
@Table(name = DatabaseConstants.PRODUCT_TABLE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_PRODUCT)
    private Long id;

    @Column(name = DatabaseConstants.PRODUCT_DESCRIPTION)
    private String productDescription;

    @Column(name = DatabaseConstants.PRODUCT_QUANTITY)
    private double quantity;

    @Column(name = DatabaseConstants.PRODUCT_UNIT_OF_MEASURE)
    private String unitOfMeasure;

    public Product () {

    }

    public Product(String productDescription, double quantity, String unitOfMeasure) {
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
