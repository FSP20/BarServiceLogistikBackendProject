package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;
import com.example.sprotte.entity.contentratio.ContentRatioProductBarContainer;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.PRODUCT_TABLE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_PRODUCT)
    private Long id;

    @Column(name = DatabaseConstants.PRODUCT_DESCRIPTION)
    private String description;

    @Column(name = DatabaseConstants.PRODUCT_QUANTITY)
    private double quantity;

    @Column(name = DatabaseConstants.PRODUCT_UNIT_OF_MEASURE)
    private String unitOfMeasure;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ContentRatioProductBarContainer> ratioProductBarContainers = new ArrayList<>();

    public Product () {

    }

    public Product(String description, double quantity, String unitOfMeasure) {
        this.description = description;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
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

    public List<ContentRatioProductBarContainer> getRatioProductBarContainers() {
        return ratioProductBarContainers;
    }

    public void setRatioProductBarContainers(List<ContentRatioProductBarContainer> ratioProductBarContainers) {
        this.ratioProductBarContainers = ratioProductBarContainers;
    }
}
