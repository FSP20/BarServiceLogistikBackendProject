package com.example.sprotte.dto.product;

public class SaveNewProductDto {

    private String productDescription;

    private double quantity;

    private String UnitOfMeasure;

    public SaveNewProductDto(String productDescription, double quantity, String unitOfMeasure) {
        this.productDescription = productDescription;
        this.quantity = quantity;
        UnitOfMeasure = unitOfMeasure;
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
        return UnitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        UnitOfMeasure = unitOfMeasure;
    }
}
