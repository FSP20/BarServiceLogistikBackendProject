package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;

import javax.persistence.*;

@Entity
@Table(name = DatabaseConstants.INGREDIENT_TABLE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_INGREDIENT)
    private Long id;

    @Column(name = DatabaseConstants.INGREDIENT_QUANTITY)
    private double quantity;

    @Column(name = DatabaseConstants.INGREDIENT_UNIT_OF_MEASURE)
    private String unitOfMeasure;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name=DatabaseConstants.ID_INGREDIENT, nullable = false)
    private Product product;

    public Ingredient() {

    }

    public Ingredient(double quantity, String unitOfMeasure) {
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
