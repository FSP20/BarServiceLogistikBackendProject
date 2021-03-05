package com.example.sprotte.dto.product;

public class SaveContentRatioProductStorageDto {

    private Long productId;
    private Long storageId;
    private int maxQuantity;
    private int actualQuantity;
    private int threshold;

    public SaveContentRatioProductStorageDto(Long productId, Long storageId, int maxQuantity, int actualQuantity, int threshold) {
        this.productId = productId;
        this.storageId = storageId;
        this.maxQuantity = maxQuantity;
        this.actualQuantity = actualQuantity;
        this.threshold = threshold;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
