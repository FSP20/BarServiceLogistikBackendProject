package com.example.sprotte.dto.bar;

public class UpdateBarContainerDto {

    Long barContainerId;
    String barContainerDescription;
    Long barSegmentId;
    Long containerCategoryId;

    public UpdateBarContainerDto(Long barContainerId, String barContainerDescription, Long barSegmentId, Long containerCategory) {
        this.barContainerId = barContainerId;
        this.barContainerDescription = barContainerDescription;
        this.barSegmentId = barSegmentId;
        this.containerCategoryId = containerCategory;
    }

    public Long getBarContainerId() {
        return barContainerId;
    }

    public void setBarContainerId(Long barContainerId) {
        this.barContainerId = barContainerId;
    }

    public String getBarContainerDescription() {
        return barContainerDescription;
    }

    public void setBarContainerDescription(String barContainerDescription) {
        this.barContainerDescription = barContainerDescription;
    }

    public Long getBarSegmentId() {
        return barSegmentId;
    }

    public void setBarSegmentId(Long barSegmentId) {
        this.barSegmentId = barSegmentId;
    }

    public Long getContainerCategoryId() {
        return containerCategoryId;
    }

    public void setContainerCategoryId(Long containerCategoryId) {
        this.containerCategoryId = containerCategoryId;
    }
}
