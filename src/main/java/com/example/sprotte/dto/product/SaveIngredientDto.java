package com.example.sprotte.dto.product;

public class SaveIngredientDto {

    private double quantity;
    private String unitOfMeasure;
    private Long productId;

    public SaveIngredientDto() {

    }

    public SaveIngredientDto(double quantity, String unitOfMeasure, Long productId) {
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
