package com.example.sprotte.dto.product;

public class UpdateProductDto {

    private Long productId;

    private String productDescription;

    private double quantity;

    private String UnitOfMeasure;

    public UpdateProductDto(Long productId, String productDescription, double quantity, String unitOfMeasure) {
        this.productId = productId;
        this.productDescription = productDescription;
        this.quantity = quantity;
        UnitOfMeasure = unitOfMeasure;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
