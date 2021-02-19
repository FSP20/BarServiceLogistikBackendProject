package com.example.sprotte.dto.product;

public class SaveContentRatioProductBarContainerDto {

    private Long productId;
    private Long barContainerId;
    private int maxQuantity;
    private int actualQuantity;
    private int threshold;

    public SaveContentRatioProductBarContainerDto(Long productId, Long barContainerId, int maxQuantity, int actualQuantity, int threshold) {
        this.productId = productId;
        this.barContainerId = barContainerId;
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

    public Long getBarContainerId() {
        return barContainerId;
    }

    public void setBarContainerId(Long barContainerId) {
        this.barContainerId = barContainerId;
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
